package org.ryuuzakiumi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ryuuzakiumi.dto.LoginDTO;
import org.ryuuzakiumi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest requset) {
		String id = requset.getParameter("id");
		String pw = requset.getParameter("pw");

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setId(id);
		loginDTO.setPw(pw);

		LoginDTO login = loginService.login(loginDTO);
		if (login.getCount() == 1) { // 정상 로그인 체크용으로 만든 필드
			// 세션
			HttpSession session = requset.getSession();
			session.setAttribute("mid", id);
			session.setAttribute("mname", login.getMname());
		}

		return "redirect:/index";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mid") != null) {
			session.removeAttribute("mid");
		}
		if (session.getAttribute("mname") != null) {
			session.removeAttribute("mname");
		}
		session.invalidate();
		return "redirect:/index";
	}
}
