package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;


/*
	DAO
	1. Database Access Object
	2. Database에 접근해서 쿼리문을 실행하고 쿼리문의 실행 결과를 받아온다.
	3. 여러 객체가 만들어 지지 않도록 singleton 패턴으로 생성한다.
*/

public class BoardDAO {

	// 모든 메소드가 사용할 공통 필드
	private Connection con;			// Oracle DataBase와 연결할 때 사용한다.
	private PreparedStatement ps;	// 자바에서 쿼리문을 작성하기 위해 사용한다.
	private ResultSet rs;			// SELECT문의 결과를 저장하는 객체이다.
	private String sql;
	
	// Connection 관리를 위한 DataSource 필드
	private DataSource dataSource;
	
	// Singleton Pattern으로 DAO 생성하기
	// Singleton Pattern : 객체의 인스턴스가 오직 1개만 생성되는 패턴을 의미한다.
	// 외부에서 객체 생성되지 않도록 private 처리.
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기 (JNDI 방식)
		try {
			// InitialContext()는 웹 어플리케이션이 처음으로 배치될때 설정되고, 모든 설정된 엔트리와 자원은 JNDI namespace의 java:comp/env 부분에 놓이게 된다.
			Context context = new InitialContext();
			// context의 lookup메서드를 이용해서 "java:comp/env" 에 해당하는 객체를 찾아서 envContext에 삽입
			Context envContext = (Context)context.lookup("java:comp/env");
			// envContext의 lookup메서드를 이용해서 "java/GDJ61"에 해당하는 객체를 찾아서 dataSource에 삽입
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61");
			/*
				Context context = new InitialContext();
				dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
			*/
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static BoardDAO getInstance() {		// 메소드가 static 처리되려면 클래스가 static 처리되어야 한다.
		return dao;
	}
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 온다.
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList를 만든다.
			while(rs.next()) {
				
				// Step1. Board 테이블의 결과 행(ROW)을 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// Step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// Step3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. ArrayList 반환
		return boardList;
		
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		return new BoardDTO(1, "제목", "내용", null, new Date(System.currentTimeMillis()));
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		// 1. 삽입 결과 변수 선언
		int insertResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 온다.
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, NULL, SYSDATE)";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle()); 		// 1번째 물음표(?)에 title 전달하기
			ps.setString(2, board.getContent());	// 2번째 물음표(?)에 content 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(INSERT문 실행은 executeUpdate 메소드로 한다.)
			insertResult = ps.executeUpdate();

		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원을 반납한다.
			close();
		}
		
		// 7. 삽입 결과 반환
		return insertResult;
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		// 1. 삭제 결과 변수 선언
		int deleteResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 온다.
			con = dataSource.getConnection();
						
			// 3. 실행할 쿼리문
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
						
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
						
			// 5. 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no); 		// 1번째 물음표(?)에 board_no 전달하기
						
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(DELETE문 실행은 executeUpdate 메소드로 한다.)
			deleteResult = ps.executeUpdate();
			
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원을 반납한다.
			close();
		}
		
		return deleteResult;
	}
	
}