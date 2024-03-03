package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileAddController implements Controller {
	
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String UPLOAD_DIR="file_repo";
		// 파일이름 file_repo
		String uploadPath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		//
		File currentDirPath=new File(uploadPath); // 업로드할 경로를 File 객체로 만들기
		if(!currentDirPath.exists()) {

			currentDirPath.mkdir();
		}
		
		// 파일 업로드 -> 임시 저장 디렉 -> 실제 저장 디렉
		// 파일 업로드 -> 임시&실제 디렉 (동시에 같이 씀)
		// file upload 필요한 API - commons-fileupload, commons-io //
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 파일들이 저장되는 Factory
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024); 
		
		String fileName = null;
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try { // items -- > fileitem[], [], [] 
			List<FileItem> items = upload.parseRequest(request);
			for (int i=0 ; i < items.size(); i++) {
				FileItem fileItem= items.get(i);
				if(fileItem.isFormField()) {
					//폼필드이면
					System.out.println(fileItem.getFieldName()+"="+ fileItem.getString("utf-8"));
				}else {//파일이면
					if(fileItem.getSize()>0) {
						int idx = fileItem.getName().lastIndexOf("\\"); // \\ 윈도우 경로
						if(idx==-1) {
							idx=fileItem.getName().lastIndexOf("/"); // / 리눅스 경로
						}
						fileName = fileItem.getName().substring(idx+1); //파일이름
						File uploadFile=new File(currentDirPath+"\\"+fileName);
						// 파일이 중복체크
						if(uploadFile.exists()) {
							fileName=System.currentTimeMillis()+"_"+fileName;
							uploadFile=new File(currentDirPath+"\\"+fileName);
						} 
						fileItem.write(uploadFile); //임시경로 -> 새로운 경로에 파일에 파일 쓰기
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// $.ajax()쪽으로 업로드된 최종 파일이름을 전송시켜준다.
		response.setContentType("text/html;charset=euc-kr");
		response.getWriter().print(fileName);
		System.out.println(fileName);
		return null;
	}

}
