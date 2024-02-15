package org.ryuuzkaiumi.service;

import java.util.List;

import org.ryuuzkaiumi.dao.BoardDAO;
import org.ryuuzkaiumi.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<BoardDTO> boardList(){
		return boardDAO.boardList();
	}

}
