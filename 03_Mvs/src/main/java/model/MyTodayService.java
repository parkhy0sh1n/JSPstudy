package model;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyTodayService implements MyService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("today", new Date(System.currentTimeMillis()));
		return "view/output.jsp";
		
	}

}
