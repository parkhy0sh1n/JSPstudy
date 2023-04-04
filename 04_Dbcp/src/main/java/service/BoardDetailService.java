package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardDetailService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		int board_no = Integer.parseInt(opt.orElse("0"));
		
		// 2. BoardDAO의 selectBoardByNo 메소드를 호출
		BoardDTO board = BoardDAO.getInstance().selectBoardByNo(board_no);
		
		// 3. DB에서 가져온 게시글(BoardDTO board)을 request에 저장(상세보기 화면(board/detail.jsp)으로 forword(전달)하기 위해서)한다.
		request.setAttribute("board", board);
		
		// 4. 어디로 and 어떻게 이동
		return new ActionForward("board/detail.jsp", false);
		
	}

}
