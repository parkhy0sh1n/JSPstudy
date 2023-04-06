package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class BbsDetailService implements IBbsService {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bbsNo"));
		int bbsNo = Integer.parseInt(opt.orElse("0"));
		
		System.out.println(bbsNo);
		
		return null;
		
	}
	
}
