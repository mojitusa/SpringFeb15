package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dao.BoardDAO;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.CommentDTO;
import org.ryuuzakiumi.dto.WriteDTO;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired Util util;
	
	public List<BoardDTO> boardList(int currentPageNo){
		return boardDAO.boardList(currentPageNo);
	}

	public BoardDTO detail(int no) {
		//2024-02-22 psd 요구사항 확인 
		//로그인 했어? -> 조회수 올리기
		if (util.getSession().getAttribute("mid") != null) {
			//DTO 만들기 - 번호 + 아이다
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(no);
			dto.setMid((String) util.getSession().getAttribute("mid"));
			//int result = boardDAO.readFlag(dto);  //이미 읽었나?
			//if (result == 0) { //안 읽었다면
				boardDAO.viewCountup(dto);
			//}
		}
		
		return boardDAO.detail(no);
	}

	public int write(WriteDTO dto) {
		//HttpServletRequest request = util.req();
		//HttpSession session = util.getSession();
		
		//엔터키 처리
		dto.setContent(dto.getContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		dto.setMid((String) util.getSession().getAttribute("mid"));
		dto.setIp(util.getIP());
		return boardDAO.write(dto);
	}
	
	public int commentWrite(CommentDTO comment) {
		//댓글 내용 + 글번호 + mid
		comment.setMid((String) util.getSession().getAttribute("mid"));
		comment.setCip(util.getIP());
		return boardDAO.commentWrite(comment);
	}

	public List<CommentDTO> commentsList(int reNo) {
		return boardDAO.commentsList(reNo);
	}

	public int postDel(int no) {
		//글번호 + mid 같이 보내기 (따로 보내도 상관은 없다.)
		WriteDTO dto = new WriteDTO();
		dto.setBoard_no(no);
		dto.setMid((String) util.getSession().getAttribute("mid"));
		
		return boardDAO.postDel(dto);
	}

	public int totalRecordCount() {
		return boardDAO.totalRecordCount();
	}

	public int deleteComment(int no, int cno) {
		CommentDTO dto = new CommentDTO();
		
		dto.setComment(dto.getComment().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		dto.setNo(cno);
		dto.setBoard_no(no);
		dto.setMid((String) util.getSession().getAttribute("mid"));
		
		System.out.println(no);
		System.out.println(cno);
		
		return boardDAO.deleteComment(dto);
	}

	public int likeUp(CommentDTO dto) {
		return boardDAO.likeUp(dto);
	}
	
	

}
