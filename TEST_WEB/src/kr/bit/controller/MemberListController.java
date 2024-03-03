package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberListController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Pojo 1. model 연동을 한다.
		MemberDAO dao = new MemberDAO();
		System.out.println(dao);
		List<MemberVO> list=dao.memberList();
		// Pojo 2. 객체 바인딩
		request.setAttribute("list", list);
		
		// member/memberList.jsp
		// 다음페이지는 
		// Pojo 3.다음페이지정보(view)
		String nextPage ="memberlist";
		return nextPage;
	}

}
