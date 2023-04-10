package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberModifyService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터를 이용해서 Member member 객체 만들기
		Member member = new Member();
		member.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
		member.setName(request.getParameter("name"));
		member.setGender(request.getParameter("gender"));
		member.setAddress(request.getParameter("address"));
		
		try {
			
			// Member member 객체의 정보를 이용해 DB의 내용을 수정
			int updateResult = MemberDAO.getInstance().updateMember(member);
			
			// 응답 데이터 형식 (JSON)
			response.setContentType("application/json; charset=UTF-8");
			
			// 응답 데이터 만들기
			/*
			{
				"updateResult": 1
			}
			 */
			JSONObject obj = new JSONObject();
			obj.put("updateResult", updateResult);
			
			// 응답 (try문의 응답이므로 ajax의 success로 전달된다.)
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.flush();
			out.close();			
			
		} catch(Exception e) {
			
			// 응답 데이터 형식 (일반 텍스트) -> 예외 사유를 텍스트로 전달하기 위해서
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답 코드 만들기
			response.setStatus(501);
			
			// 응답 데이터 만들기
			String msg = "회원 정보 수정이 실패했습니다. 입력 데이터를 확인하세요.";
			
			// 응답 (catch문의 응답이므로 ajax의 error로 전달된다.)
			PrintWriter out = response.getWriter();
			out.print(msg);  // println() 메소드를 사용하면 응답 메시지 뒤에 엔터가 자동으로 추가되므로 print() 메소드를 사용한다.
			out.flush();
			out.close();
			
		}
		
	}

}