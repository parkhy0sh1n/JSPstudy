package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")		// getAllBoardList.do   getBoardByNo.do   addBoard.do   modifyBoard.do   removeBoard.do

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI();					/*		/04_Dbcp/getAllBoardList.do		*/	// 프로젝트 + 파일경로까지 가져온다.
		String contextPath = request.getContextPath();					/*		/04_Dbcp					    */	// 프로젝트 Path만 가져온다
		String urlMapping = requestURI.substring(contextPath.length());	/* 		/getAllBoardList.do				*/	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
