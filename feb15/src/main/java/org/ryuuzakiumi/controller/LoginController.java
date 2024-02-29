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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	//2024-02-28 psd 애플리케이션 테스트 수행
	/*
	 * 스케치 -> 와이어프레임 -> 목업 -> 프로토타입 -> 스토리보드 
	 * 
	 * 와이어프레임 : 기획 단계의 기초를 제작하는 단계. 페이지의 레이아웃이나 
	 * UI 요소 등 뼈대
	 * 
	 * 목업 : 와이어프레임보다 조금 더 설계 화면과 유사하게 만드는 것. 정적 모델링
	 * 
	 * 프로토타입 : 다양한 인터렉션이 결합되어 실제 서비스처럼 동작하는 것
	 * 
	 * 스토리보드 : 설명, 기능 명세서, 와이어프레임, 프로세스, 정책 등등
	 * 		설계 문서
	 */
	
	//아이디 -> 중복검사
	//비밀번호1
	//비밀번호2
	//이메일 -> 중복 불가
	//닉네임
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("email"));
		
		MemberDTO join = new MemberDTO();
		join.setMid(request.getParameter("id"));
		join.setMpw(request.getParameter("pw"));
		join.setMname(request.getParameter("name"));
		join.setMemail(request.getParameter("email"));
		
		int result = loginService.join(join);
		System.out.println("회원가입 결과 : " + result);
		
		return "redirect:/login";
	}

	
	
	@GetMapping("/myjoin")
	public String myjoin() {
		
		return "myjoin";
	}
	
	@PostMapping(value="/myjoin", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String myjoin(@RequestParam("enteredId") String enteredId, Model model) {
		int dup = 0;
		dup = loginService.idDuplicationChecck(enteredId);
		
		if (dup == 0) {
			model.addAttribute("duplication", "사용할 수 있는 아이디입니다.");
			System.out.println("사용할 수 있는 아이디입니다.");
			return "사용할 수 있는.. 아이디입니다.";
		} else {
			model.addAttribute("duplication", "이미 사용 중인 아이디입니다.");
			System.out.println("이미 사용 중인 아이디입니다.");
			return "이미 사용 중인. 아이디입니다.";
		}
		
		
	}
	
	@GetMapping("/jointest")
	public String jointest() {
		
		return "jointest";
	}

}
