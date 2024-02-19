package org.ryuuzakiumi.controller;

import org.apache.ibatis.annotations.Param;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {
	
	@Autowired
	private BoardService boardService;

	@PostMapping("/restDetail")
	public @ResponseBody BoardDTO restDetail(@Param("no") int no) {
		BoardDTO detail = boardService.detail(no); 
		
		return detail;
	}
}
