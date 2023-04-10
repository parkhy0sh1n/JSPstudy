package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

/*
	SqlSessionFactory : 5장에서는 DataSource를 이용하여 Oracle DB 서버와 MyBatis를 연결시키도록 했었다.
					    하지만 조금 더 정확하게 말하면, 실질적으로 Oracle DB 서버와 MyBatis를 연결해주는 건 'SqlSessionFactory'라는 객체이다.
			  	 	    이 객체가 DataSource를 참조하여 MyBatis와 Oracle DB 서버를 연동시켜준다.
			  	 	    (SqlSession을 생성해준다.)
			  	 	    
	MyBatis 설정 파일 : 데이터베이스의 접속 주소 정보나 Mapping 파일의 경로 등의 고정된 환경정보를 설정한다.
*/

public class MemberDAO {

	private SqlSessionFactory factory;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){
		try {
			// MyBatis 설정 파일(mybatis-config.xml)
			String resource = "mybatis/config/mybatis-config.xml";
			// MyBatis 설정 파일(mybatis-config.xml)의 경로(mybatis/config/mybatis-config.xml)를 읽어 파일을 읽는다.
			InputStream in = Resources.getResourceAsStream(resource);
			// MyBatis 설정 파일(mybatis-config.xml)에서 factory(SqlSessionFactory의 객체)를 빌드한다.
			// MyBatis 설정 파일을 바탕으로 SqlSessionFactory를 생성한다.
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return dao;
	}
	
	// mapper's namespace (어떤 mapper인지 인식하기 위함)
		private final String NS = "mybatis.mapper.member.";
		
		// 메소드명은 쿼리의 id와 동일한 이름을 사용하자
		
		// 목록
		public List<Member> selectAllMembers() {
			SqlSession ss = factory.openSession();
			List<Member> members = ss.selectList(NS + "selectAllMembers");  // mybatis.mapper.member.selectAllMembers
			ss.close();
			return members;
		}
		
		// 전체 회원 수
		public int getMemberCount() {
			SqlSession ss = factory.openSession();
			int count = ss.selectOne(NS + "getMemberCount");  // mybatis.mapper.member.getMemberCount
			ss.close();
			return count;
		}
		
		// 상세
		public Member selectMemberByNo(int memberNo) {
			SqlSession ss = factory.openSession();
			Member member = ss.selectOne(NS + "selectMemberByNo", memberNo);
			ss.close();
			return member;
		}

		// 아이디 중복 체크용
		public Member selectMemberById(String id) {
			SqlSession ss = factory.openSession();
			Member member = ss.selectOne(NS + "selectMemberById", id);
			ss.close();
			return member;
		}
		
		// 삽입
		public int insertMember(Member member) {
			SqlSession ss = factory.openSession(false);
			int insertResult = ss.insert(NS + "insertMember", member);
			if(insertResult == 1) {
				ss.commit();
			}
			ss.close();
			return insertResult;
		}
		
		// 수정
		public int updateMember(Member member) {
			SqlSession ss = factory.openSession(false);
			int updateResult = ss.update(NS + "updateMember", member);
			if(updateResult == 1) {
				ss.commit();
			}
			ss.close();
			return updateResult;
		}
		
		// 삭제
		public int deleteMember(int memberNo) {
			SqlSession ss = factory.openSession(false);
			int deleteResult = ss.delete(NS + "deleteMember", memberNo);
			if(deleteResult == 1) {
				ss.commit();
			}
			ss.close();
			return deleteResult;
		}
		
	}