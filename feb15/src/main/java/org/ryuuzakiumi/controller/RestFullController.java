package org.ryuuzakiumi.controller;

import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Param;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.service.BoardService;
import org.ryuuzakiumi.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFullController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private RestService restService;

	@PostMapping("/restDetail")
	public BoardDTO restDetail(@Param("no") int no) {
		BoardDTO detail = boardService.detail(no); 
		
		return detail;
	}
	
	@PostMapping("/emailAuth")
	public int emailAuth() throws EmailException {
			return restService.sendEmail();
	}
}
