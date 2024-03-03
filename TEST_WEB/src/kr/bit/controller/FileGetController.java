package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileGetController implements Controller {
	
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filename = request.getParameter("filename");
		System.out.println(filename);
		
		String UPLOAD_DIR ="file_repo";
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File f = new File(uploadPath+"//"+filename);
		//다운로드 준비 HTTP 헤더 만들기
		//클라이언트가 다운받을 때 파일이름이 깨지지 않기 위해서 인코딩
		filename= URLEncoder.encode(filename, "UTF-8g");
		filename= filename.replace("+"," ");//크롬에서 공백을 +로 표현하는 경우가 있음
		//1. http 헤더에 파일의 길이를 알려준다.
		response.setContentLength((int)f.length());
		//2. http 헤더에 파일 타입을 알려준다. 
		response.setContentType("application/x-msdownload;charset=utf-8");
		//3. http 헤더 
		response.setHeader("Content-Disposition", "attachment;fileanme="+filename+";");
		response.setHeader("Content-Transefer-Encoding","binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		//실제 다운로드하는 부분
		FileInputStream in = new FileInputStream(f);//파일읽기를 준비
		OutputStream out= response.getOutputStream();
		byte[] buffer=new byte[104];
		while(true) {
			int count= in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer, 0, count); // 0부터 100%까지
		}
		in.close();
		out.close();
		
		
		
		//다운로드
		return null;

	}
}
