package com.yc.news.servlet;

import static com.yc.news.util.ServletUtil.getUriName;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;

import com.yc.news.entity.Comment;
import com.yc.news.service.CommentService;
import com.yc.news.service.impl.commentServiceImpl;


@WebServlet("/comment/*")
public class CommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService commentService;
	public CommentServlet(){
		commentService=new commentServiceImpl();
	}
	    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uriName = getUriName(request.getRequestURI());
		LogManager.getLogger().debug("请求"+uriName+"进入CommentServlet中，做请求响应处理");
		switch (uriName) {// 接受(index.jsp)中的请求并做相应处理
		case "list":
			doList(request, response);
			break;
		case "add":
			doAdd(request, response);
			break;
		default:
			respOutStr(response, "<h1>没有对应的请求资源</h1>");
		}
	}
	
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Comment comment= getReqParam2Obj(request, Comment.class);// 请求参数转换成请求对象
		System.out.println(comment);
		
		if(commentService.addComment(comment)){
			respOutStr(response, doJsonStr(comment));
		}else{
			respOutStr(response, "false");
		}
		
		
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("cnid");//页面大小
		LogManager.getLogger().debug("取到的新闻ID="+id);
		
		List<Comment> comments=commentService.listCommentsByNewsId(id);
		respOutStr(response, doJsonStr(comments));
	}

	

}
