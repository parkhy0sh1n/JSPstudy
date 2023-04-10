package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberAddService;
import service.MemberDetailService;
import service.MemberListService;
import service.MemberModifyService;
import service.MemberRemoveService;

@WebServlet("*.do")

/*
	 	Rest 방식 : HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, 
	 			    HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.
					즉, REST는 자원 기반의 구조(ROA, Resource Oriented Architecture) 설계의 중심에 Resource가 있고,
					HTTP Method를 통해 Resource를 처리하도록 설계된 아키텍쳐를 의미한다.
				 	웹 사이트의 이미지, 텍스트, DB 내용 등의 모든 자원에 고유한 ID인 HTTP URI를 부여한다.
				 	
					CRUD Operation
					Create : 생성(POST)
					Read : 조회(GET)
					Update : 수정(PUT)
					Delete : 삭제(DELETE)
					HEAD: header 정보 조회(HEAD)
 */

public class MemberRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// URLMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 모든 서비스의 공통 타입(인터페이스)
		IMemberService service = null;
		
		// URLMapping에 따른 서비스 선택
		switch(urlMapping) {
		case "/list.do":
			service = new MemberListService();
			break;
		case "/detail.do":
			service = new MemberDetailService();
			break;
		case "/add.do":
			service = new MemberAddService();
			break;
		case "/modify.do":
			service = new MemberModifyService();
			break;
		case "/remove.do":
			service = new MemberRemoveService();
			break;
		}
		
		// 선택된 서비스 실행
		if(service != null) {
			try {
				service.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}