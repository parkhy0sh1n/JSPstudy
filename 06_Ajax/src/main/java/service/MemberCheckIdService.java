package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberCheckIdService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터 (사용자가 입력한 id)
		String id = request.getParameter("id");

		// 사용자가 입력한 id를 가진 회원 조회
		Member member = MemberDAO.getInstance().selectMemberById(id);
			
		// 응답 데이터 형식 (JSON)
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기
		/*
			- 사용 가능한 아이디인 경우
			{
				"isAvailable": true
			}
			- 사용 불가능한 아이디인 경우
			{
				"isAvailable": false
			}
		*/
		JSONObject obj = new JSONObject();
		obj.put("isAvailable", member == null);  // 사용자가 입력한 id를 가진 회원이 없으면 사용할 수 있는 id이다.
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();
		
	}

}