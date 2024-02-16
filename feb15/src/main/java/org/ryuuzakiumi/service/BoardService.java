package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dao.BoardDAO;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<BoardDTO> boardList(){
		return boardDAO.boardList();
	}

	public BoardDTO detail(int no) {
		//문자? util에 숫자로 변경해 주는 메소드 만들기
		return boardDAO.detail(no);
	}

}
