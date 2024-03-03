package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1.파라미터 수집(VO)
		int num = Integer.parseInt(request.getParameter("num"));
		int age = Integer.parseInt(request.getParameter("age"));
		String email = (request.getParameter("email"));
		String phone = (request.getParameter("phone"));
		String nextPage = null;
		//MemverVo vo = new Member(id, pass, name, age, email, phone);
		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		String ctx=request.getContextPath(); //MVC04
		MemberDAO dao = new MemberDAO();
		
		int cnt = dao.memberUpdate(vo);

		//PrintWriter out = response.getWriter();
				
		if(cnt>0) {
			//out.println("insert success");
			//다시 회원리스트 보기로 복귀
			nextPage ="redirect:"+ctx+"/memberlist.do";
			
		} else {	
			// 가입실행 -> 예외 객체를 만들어서 WAS에 던지자.
			throw new ServletException("not Update");
			
		}
		return nextPage;
			
	}

}
