package ex11_upload_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// 다운로드 해야 할 File 객체
		File file = new File(path);
		
		// 다운로드 해야 할 파일을 읽어들일 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드용 응답 헤더 작업
		// Content-disposition : 응답 본문을 브라우저가 어떻게 표시해야 할지 알려주는 헤더이다.
		// attachment : 다운로드 속성
		response.setHeader("Content-disposition", "attachment");
		// Content-Lenght : 메시지 본문의 길이(바이트)를 설정하거나 리턴한다.
		response.setHeader("Content-Lenght", file.length() + "");	// String으로 변환해주어야 한다.
		
		// 응답 스트림(출력 스트림)
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사 (in에서 1024바이트 단위로 읽은 다음 out으로 보내기)
		byte[] b = new byte[1024];	// 입력 단위
		int readByte = 0;			// 실제로 읽은 바이트
		while((readByte = in.read(b)) != -1) {
			out.write(b, 0, readByte);
		}
		
		out.close();
		in.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
