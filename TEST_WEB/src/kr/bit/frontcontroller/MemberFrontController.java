package kr.bit.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("uTF-8");	
	// 클라이언트가 어떤 요청을 했는지 파악하기
	String url = request.getRequestURI();
	System.out.println(url); //MVC04/memberlist.do 리턴
	
	String ctx=request.getContextPath();
	System.out.println(ctx); //MVC04 리턴
	
	// 실제로 요청한 명령이 무엇인지 파악
	String command = url.substring(ctx.length());
	System.out.println(command); //memberlist.do 리턴
	

	//요청에 따른 분기작업
	Controller controller = null;
	String nextPage =null;
	
	//핸들러 맵핑-> HandlerMapping
//	if(command.equals("/memberlist.do")) {
//		controller = new MemberListController();
//		nextPage = controller.requestHandler(request, response);
//	
//		
//	} else if (command.equals("/memberInsert.do")) {
//		controller = new MemberInsertController();
//		nextPage = controller.requestHandler(request, response);  
//	
//		
//	} else if (command.equals("/memberContent.do")) {
//		controller = new MemberContentController();
//		nextPage = controller.requestHandler(request, response);
//
//		
//	} else if (command.equals("/memberRegister.do")) {
//		controller = new MemberRegisterController();
//		nextPage = controller.requestHandler(request, response);  
//
//		
//	} else if (command.equals("/memberDelete.do")) {
//		controller = new MemberDeleteController();
//		nextPage = controller.requestHandler(request, response);  
//
//	} else if (command.equals("/memberUpdate.do")) {
//		controller = new MemberUpdateController();
//		nextPage = controller.requestHandler(request, response);  
//
//		
//	}
//	
	HandlerMapping mapping = new HandlerMapping();
	controller = mapping.getController(command);
	nextPage = controller.requestHandler(request, response);
	
	//forward와 redirect 구분하기
	if(nextPage!=null){
		if(nextPage.indexOf("redirect:") != -1) {
			 response.sendRedirect(nextPage.split(":")[1]);
		}else {
			System.out.println("nextPage : " +nextPage);
			RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(nextPage));
			rd.forward(request, response);
			}
	}

	
	}
}
