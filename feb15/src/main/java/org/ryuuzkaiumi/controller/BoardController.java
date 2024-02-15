package org.ryuuzkaiumi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ryuuzkaiumi.dto.BoardDTO;
import org.ryuuzkaiumi.service.BoardService;
import org.ryuuzkaiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping({"/board", "/"})
	public String board(Model model) {
		List<BoardDTO> list = boardService.boardList();
		model.addAttribute("list", list);
		
		return "board";
		
	}
	
	//2024-02-15
	@GetMapping("/detail")
	public String detail(HttpServletRequest request) {
		
		//오는 매개변수 no 잡기
		String no = request.getParameter("no");
		
		return "detail";
	}
}
