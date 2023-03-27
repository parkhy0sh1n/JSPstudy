package ex11_upload_download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
			파일 업로드를 위한 작업
			1. http://servlets.com에 방문한다.
			2. COS File Upload Library 메뉴에 있는 cos-22.05.zip 파잃을 다운로드 받는다.
			3. 압축 해제 후 lib 디렉터리에 있는 cos.jar 파일을 프로젝트의 lib로 등록한다.
		 */
		
		/*
			cos.jar
			1. 파일 업로드가 필요요한 경우에 사용하는 라이브러리이다.
			2. HttpServletRequest 클래스는 파일 업로드를 처리할 수 없다.
			3. MultupartRequest 클래스를 이용해서 파일 업로드를 처리할 수 있는데, cos.jar가 MultipartRequest 클래스를 지원한다.
		 */
		
		/*
			ServletContext : 서블릿 컨테이너와 통신하기 위해서 사용되는 메소드를 지원하는 인터페이스이다.
			서블릿 컨테이너 : 서버에 만들어진 서블릿이 스스로 작동하는 것이 아니라, 서블릿을 관리 해주는 것이 필요하다.
							  이러한 역할을 하는 것이 바로 서블릿 컨테이너이다.
			getRealPath() : webapp 폴더를 의미한다.
			DefaultFileRenamePolicy() : 보통 우리에게 친숙한 파일명 설정방식이다. 
										동일한 파일이름이 존재한다면, test1.jpg, test2.jpg...
										이렇게 숫자를 붙여서 새로운 파일처럼 업로드될 디렉토리에 파일을 저장하는 것을 의미한다.
			getOriginalFileName() : 클라이언트가 업로드한 파일의 원본 이름을 반환한다.
			getFilesystemName() : 서버에 실제로 업로드된 파일의 이름을 반환한다. 
		 */
		
		// 업로드 경로 (서버의 real path를 사용하거나 일반 경로를 사용할 수 있다.)
		String realPath = request.getServletContext().getRealPath("storage");
		File dir = new File(realPath);
		if(dir.exists() == false) {		// dir 경로의 폴더를 찾는다.
			dir.mkdirs();				// 폴더가 없으면 만든다.
		}
		
		// 업로드 진행하기 (HttpServletRequest request를 이용해서 MultipartRequest 객체를 생성하면 업로드가 곧바로 진행된다.)
		MultipartRequest multipartRequest = new MultipartRequest(
				request, 						// HttpServletRequest
				realPath,						// 업로드 경로	
				1024 * 1024 * 10,				// 최대 크기
				"UTF-8",						// 인코딩
				new DefaultFileRenamePolicy()	// 파일명 중복 처리 정책 (파일명 뒤에 넘버링)
		);
		
		// 요청 파라미터 (MultipartRequest multipartRequest를 이용해서 파라미터를 가져와야 한다.)
		String uploader = multipartRequest.getParameter("uploader");
		String originName = multipartRequest.getOriginalFileName("filename");	// 원래 첨부 파일명
		String filesystemName = multipartRequest.getFilesystemName("filename");	// 저장된 첨부 파일명
		
		// 첨부 파일 정보
		// 파일명, 경로, 최종수정일(yyyy-MM-dd), 파일크기(KB)
		/*
			SimpleDateFormat : 날짜 형식 변경
			format : 문자열 형식 변경
			DecimalFormat : 숫자 형식 변경 
		 */
		File file = multipartRequest.getFile("filename");	// 파일읋 file 객체에 저장한다.
		String name = file.getName();						// file 객체에서 파일명을 불러온다.
		String parent = file.getParent();					// file 객체에서 경로를 불러온다.
		String lastModified = new SimpleDateFormat("yyyy-MM-dd").format(file.lastModified());	// file 객체에서 최종수정일을 불러온다.
		String size = new DecimalFormat("#,##0").format(file.length() / 1024 + (file.length() % 1024 != 0 ? 1 : 0));
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<ul style=\"list-style-type: circle; font-size: 24px;\">");
		out.println("<li>작성자 : " + uploader + "</li>");
		out.println("<li>원래 첨부 파일명 : " + originName + "</li>");
		out.println("<li>저장된 첨부 파일명 : " + filesystemName + "</li>");
		out.println("<li>파일명 : " + name + "</li>");
		out.println("<li>경로 : " + parent + "</li>");
		out.println("<li>최종수정일 : " + lastModified + "</li>");
		out.println("<li>파일크기 : " + size + "KB</li>");
		out.println("<li><a href=\"/01_Servlet/FileListServlet?parent=" + URLEncoder.encode(parent, "UTF-8") + "\">첨부된 파일 목록 보기</a></li>");
		out.println("</ul>");
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
