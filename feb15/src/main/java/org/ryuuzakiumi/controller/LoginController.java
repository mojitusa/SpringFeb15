package org.ryuuzakiumi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.ryuuzakiumi.dto.LoginDTO;
import org.ryuuzakiumi.dto.MemberDTO;
import org.ryuuzakiumi.service.LoginService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private Util util;

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

		// 제대로 된 ID + 비밀번호 실패 -> count -> 5회 누적 시 계정 잠금 (5이상 유지)
		// 조건 : ID, PW, grade, loginAttemptCount

		LoginDTO login = loginService.loginIDCheck(loginDTO);
		// 일단 아이디가 존재하는지 확인한다.

		if (login.getMname() != null) { // ID가 존재하면
			// 등급 확인과 비밀번호 체크

			// 우선 타당한 사용자인지부터 체크
			if (loginService.validUserCheck(loginDTO) == null) { // 로그인 제한 사용자
				return "redirect:/login?validity=666";
				// validity라는 인자에 값을 주고 리다이렉트

			} else {
				// 비밀번호 체크
				if (loginService.loginCheck(loginDTO) == null) {
					// 안 들어온다면 비밀번호 오류
					// 로그인 시도 횟수 업
					loginService.loginCountUp(loginDTO);
					return "redirect:/login?mLoginAttemptCount="+loginService.loginAttemptCount(loginDTO).getMLoginAttemptCount();
				} else {
					login = loginService.loginAttemptCheck(loginDTO);
					if (login == null) {
						//비밀번호가 맞고 횟수가 초과됐다면
						return "redirect:/login?mLoginAttemptCount="+login.getMLoginAttemptCount();
					} else {
						//비밀번호가 맞고 횟수가 초과되지 않았다면
						//로그인 시도 회수 초기화 하고 메인으로 리다이렉트
						HttpSession session = requset.getSession();
						session.setAttribute("mid", id);
						session.setAttribute("mname", login.getMname());						
						loginService.loginCountReset(loginDTO);
						return "redirect:/index";
					}
				}
			}

		}

		// 세션
		HttpSession session = requset.getSession();
		session.setAttribute("mid", id);
		session.setAttribute("mname", login.getMname());
		// loginService.loginCountReset(loginDTO);
		return "redirect:/index";

//		if (login.getCount() == 1 && login.getLoginTryCount() < 5) { // 정상 로그인 체크용으로 만든 필드
//			if(login.getPw().equals(loginDTO.getPw())) { //비밀번호 비교
//				
//			// 세션
//			HttpSession session = requset.getSession();
//			session.setAttribute("mid", id);
//			session.setAttribute("mname", login.getMname());
//			loginService.loginCountReset(loginDTO);
//			return "redirect:/index";
//			} else {
//				//loginCountUp
//				loginService.loginCountUp(loginDTO);
//				return "redirect:/login?count="+login.getLoginTryCount();
//			}
//			
//			
//		} else if(login.getLoginTryCount() < 5) {
//			return "redirect:/login?count="+login.getLoginTryCount();
//			
//		} else {
//			//잘못된 로그인일 경우 로그인 창으로 이동하기 - 5번 시도했으면 계정 lock
//			//해당 ID의 mLoginCount를 +1 시키기
//			loginService.loginCountUp(loginDTO);
//			return "redirect:/login?login=1024";
//		}

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

	@GetMapping("/myInfo@{id}")
	public String myInfo(@PathVariable("id") String id) throws EmailException {
		// System.out.println("id : " + id);
		
		//로그인 여부 체크
		if (util.getSession().getAttribute("mid") != null) {
			
			//인증 요청하기 = ajax용으로 빼 두기
			//loginService.myInfo();
			
			return "myinfo";
		} else {
			return "redirect:/login?error=error";
		}
			
	}

}
