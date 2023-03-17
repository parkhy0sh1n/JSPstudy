package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		/*
			HttpServletRequest
			1. Http프로토콜의 request 정보를 서블릿에게 전달하기 위한 목적으로 사용한다.
			2. Header정보, Parameter, Cookie, URI, URL 등의 정보를 읽어들이는 메소드를 가진 클래스이다.
			3. Body의 Stream을 읽어들이는 메소드를 가지고 있다.
			
			HttpServletResponse
			1. Servlet은 HttpServletResponse객체에 Content Type, 응답코드, 응답 메시지등을 담아서 전송한다.
		 */
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이전(첫 번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet1 : " + model);
		
		// 포워드(전달)
		// RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 
		// 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스이다.
		request.getRequestDispatcher("/ForwardServlet2").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
