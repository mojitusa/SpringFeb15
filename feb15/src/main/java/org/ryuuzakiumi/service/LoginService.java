package org.ryuuzakiumi.service;

import org.apache.commons.mail.EmailException;
import org.ryuuzakiumi.dao.LoginDAO;
import org.ryuuzakiumi.dto.LoginDTO;
import org.ryuuzakiumi.dto.MemberDTO;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired LoginDAO loginDAO;
	@Autowired Util util;

	public LoginDTO loginIDCheck(LoginDTO loginDTO) {
		return loginDAO.loginIDCheck(loginDTO);
	}

	public LoginDTO validUserCheck(LoginDTO loginDTO) {
		return loginDAO.validUserCheck(loginDTO);
	}
	
	public LoginDTO loginCheck(LoginDTO loginDTO) {
		return loginDAO.loginCheck(loginDTO);
	}
	
	public LoginDTO loginAttemptCount(LoginDTO loginDTO) {
		return loginDAO.loginAttemptCount(loginDTO);
	}
	
	public void loginCountUp(LoginDTO loginDTO) {
		loginDAO.loginCountUp(loginDTO);
	}

	public void loginCountReset(LoginDTO loginDTO) {
		loginDAO.loginCountReset(loginDTO);
		
	}

	public String getEmail(String email) {
		return loginDAO.getEmail(email);
	}
	
	public void muInfo() throws EmailException {
		String email = getEmail((String) util.getSession().getAttribute("mid"));
		String key = util.createKey();
		
		MemberDTO dto = new MemberDTO();
		dto.setMemail(email);
		dto.setMkey(key);
		dto.setMid((String)util.getSession().getAttribute("mid"));
		setKey(dto);

		util.sendEmail(email, key);		
	}

	public void setKey(MemberDTO dto) {
		loginDAO.setKey(dto);
	}




}
