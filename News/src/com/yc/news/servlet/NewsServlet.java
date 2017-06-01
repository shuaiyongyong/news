package com.yc.news.servlet;

import static com.yc.news.util.ServletUtil.*;//

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.news.entity.ArchiveBean;
import com.yc.news.entity.ArchiveNews;
import com.yc.news.entity.News;
import com.yc.news.entity.NewsBean;
import com.yc.news.service.NewsService;
import com.yc.news.service.impl.NewsServiceImpl;

@WebServlet("/news/*")
public class NewsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private NewsService newsService ;

	public NewsServlet() {
		newsService= new NewsServiceImpl();
	}

	/*请求响应处理方法
		关注:
			1.请求来的参数(request的数据,如:uri,parameter....)
			2.响应数据(针对异步)或跳转地址(针对正常请求)

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uriName = getUriName(request.getRequestURI());
		//String type = request.getParameter("type")== null ? "list":request.getParameter("type");
		System.out.println(uriName);
		switch (uriName ) {//接受(index.jsp)中的请求并做相应处理
		case "typeNews":
			doLeftSide(request, response);
			break;
		case "byId":
			doById(request, response);
			break;
		case "list":
			doList(request, response);
			break;
		case "reclist":
			doReclist(request, response);
			break;
		case "tlist":
			doTlist(request, response);
			break;
		case "read":
			doRead(request, response);
			break;
		case "detail":
			doDetail(request, response);
			break;
		case "rdetail":
			doRdetail(request, response);
			break;
		case "modify":
			doModify(request, response);
			break;		
		case "archive":
			doArchive(request, response);
			break;
		case "recover":
			doRecover(request, response);
			break;
		case "add":
			doAdd(request, response);
			break;
		default:
			respOutStr(response, "<h1>没有对应的请求资源</h1>");
		}
	}

	private void doRecover(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("cnid");
		boolean isSuccess =newsService.recoverNews(id);
		respOutStr(response, isSuccess + "");
		
	}

	private void doRdetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("cnid");
		ArchiveNews rnews =newsService.getRNews(id);
		respOutStr(response, doJsonStr(rnews));
	}

	private void doReclist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageSize = request.getParameter("rows");//页面大小
		String currPage = request.getParameter("page");//当前页数
		ArchiveBean archiveBean =newsService.getPartRecNews( pageSize,currPage);//分页数据 
		//System.out.println(archiveBean);
		respOutStr(response, doJsonStr(archiveBean));//做异步数据响应,(返回数据到index.jap页面)
	}

	private void doTlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageSize = request.getParameter("rows");//页面大小
		//System.out.println("news ==>" + pageSize);
		String currPage = request.getParameter("page");//当前页数
		String tid = request.getParameter("tid");
		NewsBean newsBean =newsService.getPartNew( pageSize,currPage,tid);//分页数据 
		//System.out.println(newsBean);
		respOutStr(response, doJsonStr(newsBean));//做异步数据响应,(返回数据到index.jap页面)

	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su =new SmartUpload();
		su.setCharset("utf-8");
		su.initialize(getServletConfig(), request, response);
		boolean isSucces = false;
		try {
			su.upload();
			Request req = su.getRequest();


			News n =getReqParam2Obj(req, News.class);

			isSucces = newsService.addNews(n);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		respOutStr(response,isSucces + "");

	}

	private void doArchive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("cnid");
		boolean isSuccess =newsService.archiveNews(id);
		respOutStr(response, isSuccess + "");

	}

	private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		SmartUpload su =new SmartUpload();
		su.setCharset("utf-8");
		su.initialize(getServletConfig(), request, response);
		boolean isSucces = false;
		try {
			su.upload();
			Request req = su.getRequest();

			News n =getReqParam2Obj(req, News.class);
			isSucces = newsService.modifyNews(n);

			System.out.println("news ==>" + n);

		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		respOutStr(response, "true");
	}


	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("cnid");
		News news =newsService.getNews(id);
		respOutStr(response, doJsonStr(news));
	}

	private void doRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("cnid");
		News news =newsService.getNews(id);

		request.setAttribute("news", news);

		if(request.getRemoteHost().intern()=="0:0:0:0:0:0:0:1"){
			request.setAttribute("clientIP", "127.0.0.1");
		}else{
			request.setAttribute("clientIP", request.getRemoteHost());
		}

		request.getRequestDispatcher("/page/news_read.jsp").forward(request, response);

	}

	/**
	 * 请求分页处理
	 * @param request   请求处理对象
	 * @param response	响应处理对象
	 * @throws IOException
	 */
	private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageSize = request.getParameter("rows");//页面大小
		//System.out.println("news ==>" + pageSize);
		String currPage = request.getParameter("page");//当前页数
		NewsBean newsBean =newsService.getPartNews( pageSize,currPage);//分页数据 
		//System.out.println(newsBean);
		respOutStr(response, doJsonStr(newsBean));//做异步数据响应,(返回数据到index.jap页面)
	}

	private void doById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("cnid");
		Map<String, Object> news = newsService.getNewsById(id);
		request.setAttribute("news", news);
		request.getRequestDispatcher("../page/news_modify.jsp").forward(request, response);
	}

	private void doLeftSide(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<News> newses = newsService.getNewsByType(1, 2, 5);
		respOutStr(response, doJsonStr(newses));

	}



}
