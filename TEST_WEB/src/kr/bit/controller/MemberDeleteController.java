package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String ctx=request.getContextPath(); //MVC04
		
		MemberDAO dao = new MemberDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		int cnt = dao.memberDelete(num);
		
		String nextPage = null;
		if(cnt>0) {
			request.getSession().invalidate();
			nextPage = "redirect:"+ctx+"/memberlist.do";
		}else {
			throw new ServletException("not Delete");
		}
		
		return nextPage;
	}

}
