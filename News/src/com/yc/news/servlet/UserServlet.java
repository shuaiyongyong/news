package com.yc.news.servlet;

import static com.yc.news.util.ServletUtil.getUriName;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.news.entity.Topic;
import com.yc.news.entity.TopicBean;
import com.yc.news.entity.User;
import com.yc.news.entity.UserBean;
import com.yc.news.service.UserService;
import com.yc.news.service.impl.UserServiceImpl;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1849397480385922829L;

	private UserService userService;

	public UserServlet() {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uriName = getUriName(request.getRequestURI());
		//String type = request.getParameter("type") == null ? "list" : request.getParameter("type");
		switch (uriName) {// 接受(index.jsp)中的请求并做相应处理
		case "login":
			doLogin(request, response);
			break;
		case "check":
			doCheck(request, response);
			break;
		case "upcheck":
			doUpcheck(request, response);
			break;
		case "add":
			doAdd(request, response);
			break;
		case "detail":
			doDetail(request, response);
			break;
		case "namedetail":
			doNameDetail(request, response);
			break;
		case "list":
			doList(request, response);
			break;
		case "modify":
			doModify(request, response);
			break;		
		case "archive":
			doArchive(request, response);
			break;
		default:
			respOutStr(response, "<h1>没有对应的请求资源</h1>");
		}
	}

	private void doNameDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname=request.getParameter("uname");
		User user =userService.getUserName(uname);
		respOutStr(response, doJsonStr(user));
		
	}

	private void doArchive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("usid");	
		boolean isSuccess =userService.archiveUser(id);
		respOutStr(response, isSuccess + "");
	}

	private void doUpcheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname = request.getParameter("muname");
		String upwd = request.getParameter("oldpwd");
		//System.out.println(uname+"===>"+upwd);
	    Map<String , Object> users = userService.upCheckUser(uname,upwd);
	    respOutStr(response, doJsonStr(users));
		
	}

	private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isSucces = false;
		String id=request.getParameter("usid");
		String pwd=request.getParameter("newpwd");
		System.out.println(id + ""  + pwd);
		isSucces=userService.modifyUser(pwd,id);
		respOutStr(response, isSucces + "");
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageSize = request.getParameter("rows");//页面大小
		System.out.println("topic ==>" + pageSize);
		String currPage = request.getParameter("page");//当前页数
		UserBean userBean =userService.getAllUser( pageSize,currPage);//分页数据 
		respOutStr(response, doJsonStr(userBean));//做异步数据响应,(返回数据到index.jap页面
		
	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("usid");
		User user =userService.getUser(id);
		respOutStr(response, doJsonStr(user));
		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*boolean isSucces = false;
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		System.out.println(uname +"----"  +  upwd);
		isSucces= userService.addUser(uname,upwd);
		respOutStr(response, doJsonStr(isSucces));*/
		SmartUpload su =new SmartUpload();
		su.setCharset("utf-8");
		try {
			su.initialize(getServletConfig(), request, response);
			boolean isSucces = false;
			su.upload();
			Request req = su.getRequest();
			User user = getReqParam2Obj(req, User.class);
			isSucces = userService.addUser(user);		
			System.out.println("topic ==>" + user);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		respOutStr(response, "true");
		
	}

	private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String uname = request.getParameter("uname");
		    Map<String , Object> users = userService.checkUser(uname);
		    respOutStr(response, doJsonStr(users));
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 把参数转换成请求对象;
		HttpSession session = request.getSession();
			request.setCharacterEncoding("utf-8");

			User user = getReqParam2Obj(request, User.class);// 请求参数转换成请求对象
			String username = user.getUname();
			String password = user.getUpwd();

			if (userService.findUser(username, password)) {
				session.setAttribute("loginUser", username);
				response.sendRedirect("/News/back/admin.jsp");
			} else {
				session.setAttribute("errorMsg", "用户名或密码错误！！！");
				response.sendRedirect("/News/login.jsp");
			}
		
	}

}
