package ex11_upload_download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터
		String parent = request.getParameter("parent");
		parent = URLDecoder.decode(parent, "UTF-8");
		System.out.println(parent);
		
		// parent 경로에 저장된 파일 목록
		// listFiles() : File 객체가 디렉터리인 경우 자신의 하위에 존재하는 파일 및 서브 디렉터리 목록을 구한다.
		File dir = new File(parent);
		File[] files = dir.listFiles();
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		for(File file : files) {
			out.println("<div>" + file.getName() + " <a href=\"/01_Servlet/DownloadServlet?path=" + URLEncoder.encode(file.getPath(), "UTF-8") + "\">다운로드</a></div>");
		}
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
