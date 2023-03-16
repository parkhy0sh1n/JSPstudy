package ex04_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터 처리하기
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		/*
			응답
			
			1. 서버 -> 클라이언트로 보내는 것이 응답(Response)이다.
			2. HttpServletResponse 클래스가 응답을 처리한다.
			3. 어떤 MIME 타입으로 응답할 것인지 결정해야 한다.
				MIME 타입 		: 클라이언트에게 전송된 문서의 다양성을 알려주기 위한 메커니즘이다.
				text 타입 		: 텍스트를 포함하는 모든 문서를 나타내며, 이론상으로는 인간이 읽을 수 있어야 한다.
				application 타입 : 모든 종류의 이진 데이터를 나타낸다.
					1) HTML : text/html			(태그를 만들어서 반환하는 경우)
					2) XML  : application/xml	(ajax 응답이 XML인 경우)
					3) JSON : application/json	(ajax 응답이 JSON인 경우)
		 */
		
		// 응답 만들기
		/*
			response.setCharacterEncoding("UTF-8");
			서블릿에서 화면에 데이터를 출력하기 위해서는 out.print를 사용하는데 response.setCharacterEncoding("UTF-8"); 없이 out.print("하이");를 한다면,
			'하이'가 출력되는 것이 아니라  ???? 과 같은 물음표가 출력된다.
			이러한 문제점을 해결하기 위한 방법으로 response.setCharacterEncoding("UTF-8");를 추가한다.
			 
			response.setContentType("text/html; charset=UTF-8");
			이걸 사용하지 않고, response.setCharacterEncoding("UTF-8"); 만 사용하면 '하이'라고 나오지 않고 '뀖땖'과 같이 처음보는 이상한 문자가 출력이 된다.
			이는 브라우저마다 기본적으로 문자코드를 해석하는 default가 다르기 떄문이다.
			그래서 우리는 브라우저에게  '우리는 utf-8 문자코드로 사용할 거다. utf-8로 사용해줘'라는 response.setContentType("text/html;charset=UTF-8"); 를 써주어야 한다.
		 */
		// 1. 응답할 데이터의 MIME 타입과 UTF-8 인코딩
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 응답 스트림 생성
		PrintWriter out = response.getWriter();		// PrintWriter의 응답 메소드 : append(), write(), print(), println() 등
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>모델명: " + model + "</h1>");
		out.println("<h1>가격: " + price + "원<h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();	// (혹시) 출력 스트림에 남아 있는 데이터를 모두 내보내기
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
