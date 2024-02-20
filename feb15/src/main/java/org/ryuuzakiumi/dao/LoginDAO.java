package org.ryuuzakiumi.dao;

import org.apache.ibatis.session.SqlSession;
import org.ryuuzakiumi.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public LoginDTO login(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.login", loginDTO);
	}

}
