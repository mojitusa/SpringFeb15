package org.ryuuzakiumi.controller;

import org.apache.commons.mail.EmailException;
import org.ryuuzakiumi.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//2024.12.23. 요구사항 확인 psd
@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;

	//제작 순서
	//menu에 추가 -> Controller -> jsp -> 화면 구성 -> service
	@GetMapping("/mail")
	public String mail() {
		//로그인한 사용자만 접근 가능합니다.
		return "mail";
	}
	
	//jsp -> Controller -> Service 메일 발송
	@PostMapping("/mail")
	public String mail(@RequestParam("email") String email, @RequestParam("title") String title, 
			@RequestParam("content") String content) throws EmailException {
		
		mailService.sendTextMail(email, title, content);
		mailService.sendHTMLMail(email, title, content);
		
		return "redirect:/mail";
	}
}
