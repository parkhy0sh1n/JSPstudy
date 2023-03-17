package practice02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GugudanServlet")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GugudanServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String strDan = request.getParameter("dan");
		String strNum = request.getParameter("num");
		String strResult = request.getParameter("result");
		
		// 공백 및 null 처리 후 숫자로 변환하기
		int dan = 0, num = 0, result = 0;
		if(strDan != null && strDan.isEmpty() == false) {
			dan = Integer.parseInt(strDan);
		}
		if(strNum != null && strNum.isEmpty() == false) {
			num = Integer.parseInt(strNum);
		}
		if(strResult != null && strResult.isEmpty() == false) {
			result = Integer.parseInt(strResult);
		}
		
		// 응답 타입
		response.setContentType("text/html; charset=UTF-8");
		
		// 출력 스트림 생성
		// Servlet에서 클라이언트의 요청(Request)에 대한 응답(Response) 형태는 문자(character) 또는 바이트(byte)가 될 수 있다.
		// 클라이언트의 요청에 문자 형태로 응답하려면 데이터의 흐름(Stream)을 컨트롤 해야 한다. 즉 텍스트(==문자) 형태로 응답을 보내도록 설정해야 한다.
		// HttpServletResponse 인터페이스의 상위 인터페이스인 ServletResponse의 getWriter() 메소드를 호출하면 스트림에 텍스트를 기록하는 것이 가능하다.
		PrintWriter out = response.getWriter();
		
		// 응답하기
		out.println("<script>");
		if(dan * num == result) {
			out.println("alert('정답입니다.')");
		} else {
			out.println("alert('오답입니다.')");
		}
		out.println("history.back");
		out.println("</script>");
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
