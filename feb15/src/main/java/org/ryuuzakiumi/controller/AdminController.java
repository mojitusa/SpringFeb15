package org.ryuuzakiumi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//administrator = admin
//root

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping() // url경로
	public String index2() {
		return "redirect:/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/index/") // url경로
	public String index3() {
		return "redirect:/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/index") // url경로
	public String index() {
		return "/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "admin/join";
	}

}
