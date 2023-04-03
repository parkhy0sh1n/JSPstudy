package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

/*
	DataSource 					   : DataSource는 Connection pool을 관리하고 연동할 수 있게 해주는 표준 인터페이스이다.

	DBCP(DataBase Connection Pool) : DB Connection 객체를 미리 만들어 Connection Pool에 보관하고,
									 필요할 때마다 DB에 연결된 Connection을 꺼내 사용하는 것을 의미한다.
								     Connection Pool을 사용하는 이유는 Connection 객체를 만드는 것이 큰 비용을 소모하는 작업이기 때문이다. 
									 미리 만들어진 Connection을 재사용하여 CPU의 부담과 Connection을 생성하는데 필요한 시간을 줄일 수 있다. 
 */

public interface IBoardService {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);

}
