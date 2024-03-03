package kr.bit.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//JDBC-> myBatis, JPA (db 커넥터)
public class MemberDAO {
	
	private static SqlSessionFactory sqlSessionFactory;
	// [pool db1, pool db2, pool db3 ....]
	
	//초기화 블록-프로그램실행시 딱 한번만 실행되는 코드영역
	static {
		try {
		String resource = "kr/bit/mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource); 
		//config.xml 읽기
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//config.xml 읽어와서 sqlSessionFactory Pool 객체 생성
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//회원전체 리스트보기
		public List<MemberVO> memberList() {
			// [Connection+Statement] => SqlSession
			SqlSession session= sqlSessionFactory.openSession();
		
			List<MemberVO> list=session.selectList("memberList");
			session.close(); //반납
			return list;
			
		}
		
		//회원 가입 
		public int memberInsert(MemberVO vo) {
			SqlSession session= sqlSessionFactory.openSession();
			
			int cnt = session.insert("memberInsert", vo);
			session.commit(); //반영
			session.close(); //반납
			return cnt;
		}
		
		//회원 삭제
		public int memberDelete(int num) {
			SqlSession session= sqlSessionFactory.openSession();
			
			int cnt = session.delete("memberDelete", num);
			session.commit(); //반영
			session.close(); //반납
			return cnt;
		}
		
		//회원 상세보기
		public MemberVO memberContent(int num) {
			SqlSession session= sqlSessionFactory.openSession();
			
			MemberVO vo = session.selectOne("memberContent", num);
			session.commit(); //반영
			session.close(); //반납
			return vo;
		}
		
		//회원 수정하기
		public int memberUpdate(MemberVO vo) {
			SqlSession session= sqlSessionFactory.openSession();
			
			int cnt = session.update("memberUpdate", vo);
			session.commit(); //반영
			session.close(); //반납
			return cnt;
		}
		
		// 회원 로그인
		public String memberLogin(MemberVO vo) {
			SqlSession session= sqlSessionFactory.openSession();
			String user_name = session.selectOne("memberLogin", vo);
			
			session.close(); //반납
			return user_name;
		}
		
		//아이디 중복 체크
		public String memberDbcheck(String id) {
			SqlSession session= sqlSessionFactory.openSession();
			
			String dbId=session.selectOne("memberDbcheck", id); // id or null
			String idDouble= "NO";
			if(dbId!=null) {
				idDouble="YES";
			}
			return idDouble; // YES(중복), ON(중복아님)
		}
		
		//파일 첨부 회원가입
		public int memberInsertFile(MemberVO vo) {
			SqlSession session= sqlSessionFactory.openSession();
			
			int cnt = session.insert("memberInsertFile", vo);
			session.commit(); //반영
			session.close(); //반납
			return cnt;
		}
		
}
