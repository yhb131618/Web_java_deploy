 package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

/**
 * Servlet implementation class MemberLoginController
 */
public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 세션을 제거하는 방법
		// 세션을 가져와서 세션을 제거
		// 1. 강제로
		// 2. 브라우저를 종료하는 것( 브라우저쿠키에 저장)
		// 3. 세션을 종료될 때까지 기다리는 것(세션타임아웃 : 30분=1800초
		// tomcat-web.xml session-timeout 30으로 설정되어 있다.
		
		String ctx=request.getContextPath();
		request.getSession().invalidate();
		System.out.println("logoutcontroller");
		return "redirect:" +ctx+"/memberlist.do";
		
		
	}

}
