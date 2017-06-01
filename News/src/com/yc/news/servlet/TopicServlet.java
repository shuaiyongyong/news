package com.yc.news.servlet;

import static com.yc.news.util.ServletUtil.getUriName;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.news.entity.News;
import com.yc.news.entity.NewsBean;
import com.yc.news.entity.Topic;
import com.yc.news.entity.TopicBean;
import com.yc.news.entity.User;
import com.yc.news.service.TopicService;
import com.yc.news.service.impl.TopicServiceImpl;

@WebServlet("/topic/*")
public class TopicServlet extends BaseServlet {

	private static final long serialVersionUID = 1849397480385922829L;

	private TopicService topicService;
	public TopicServlet() {
		topicService=new TopicServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uriName = getUriName(request.getRequestURI());
		String type = request.getParameter("type")== null ? "list":request.getParameter("type");
		switch (uriName ) {//接受(index.jsp)中的请求并做相应处理
		case "list":
			doList(request, response);
			break;
		case "add":
			doAdd(request, response);
			break;
		case "detail":
			doDetail(request, response);
			break;
		case "modify":
			doModify(request, response);
			break;
		case "check":
			doCheck(request, response);
			break;
		case "archive":
			doArchive(request, response);
			break;
		default:
			respOutStr(response, "没有对应的请求资源");
		}
	}
	private void doArchive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("tid");	
		boolean isSuccess =topicService.archiveTopic(id);
		respOutStr(response, isSuccess + "");
		
	}
	private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String tname = request.getParameter("tname");
	    Map<String , Object> topics = topicService.checkTopic(tname);
	    respOutStr(response, doJsonStr(topics));
	}
	private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean isSucces = false;
		String id=request.getParameter("tid");
		String name=request.getParameter("mtname");
		System.out.println(id + ""  + name);
		isSucces=topicService.modifyTopic(name,id);
		respOutStr(response, isSucces + "");
		
	}
	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("ctid");
		Topic topic =topicService.getTopic(id);
		respOutStr(response, doJsonStr(topic));
		
	}
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 把参数转换成请求对象;
		SmartUpload su =new SmartUpload();
		su.setCharset("utf-8");
		try {
			su.initialize(getServletConfig(), request, response);
			boolean isSucces = false;
			su.upload();
			Request req = su.getRequest();
			Topic topic = getReqParam2Obj(req, Topic.class);
			isSucces = topicService.findTopic(topic);	
			System.out.println("topic ==>" + topic);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		respOutStr(response, "true");
	}
	private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*List<Topic> topics=topicService.getAllTopic();
		respOutStr(response, doJsonStr(topics));*/
		
		String pageSize = request.getParameter("rows");//页面大小
		System.out.println("topic ==>" + pageSize);
		String currPage = request.getParameter("page");//当前页数
		TopicBean topicBean =topicService.getAllTopic( pageSize,currPage);//分页数据 
		respOutStr(response, doJsonStr(topicBean));//做异步数据响应,(返回数据到index.jap页面
	} 
}
