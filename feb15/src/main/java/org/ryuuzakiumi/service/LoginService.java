package org.ryuuzakiumi.service;

import org.ryuuzakiumi.dao.LoginDAO;
import org.ryuuzakiumi.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired LoginDAO loginDAO;

	public LoginDTO login(LoginDTO loginDTO) {
		return loginDAO.login(loginDTO);
	}

}
