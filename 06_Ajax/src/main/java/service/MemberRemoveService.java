package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDAO;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터(삭제해야 할 회원 번호)
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		// int memberNo 객체의 정보를 이용해 DB의 내용을 삭제
		int deleteResult = MemberDAO.getInstance().deleteMember(memberNo);
		
		// 응답 데이터 형식 (JSON)
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기
		/*
			{
				"deleteResult": 1
			}
		*/
		JSONObject obj = new JSONObject();
		obj.put("deleteResult", deleteResult);
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();

	}

}