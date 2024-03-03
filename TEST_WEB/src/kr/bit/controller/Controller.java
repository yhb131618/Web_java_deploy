package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 pojo가 공통으로 가지고 있어야 하는 추상메서드 생성
// 모든 pojo가 가지고 있어야 되는 메서드
// 리턴타입 String(URL 경로를 반환하기 떄문에)
public interface Controller {
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	
}
