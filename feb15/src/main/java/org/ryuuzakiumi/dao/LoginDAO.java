package org.ryuuzakiumi.dao;

import org.apache.ibatis.session.SqlSession;
import org.ryuuzakiumi.dto.LoginDTO;
import org.ryuuzakiumi.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO extends AbstractDAO {

	public LoginDTO loginIDCheck(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.loginIDCheck", loginDTO);
	}

	public LoginDTO validUserCheck(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.validUserCheck", loginDTO);
	}
	
	public LoginDTO loginCheck(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.loginCheck", loginDTO);
	}
	
	public LoginDTO loginAttemptCount(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.loginAttemptCount", loginDTO);
	}
	
	public void loginCountUp(LoginDTO loginDTO) {
		sqlSession.update("login.loginCountUp", loginDTO);
	}

	public LoginDTO loginAttemptCheck(LoginDTO loginDTO) {
		return sqlSession.selectOne("login.loginAttemptCheck", loginDTO);
	}
	
	public void loginCountReset(LoginDTO loginDTO) {
		sqlSession.update("login.loginCountReset", loginDTO);
	}

	public String getEmail(String email) {
		return sqlSession.selectOne("login.getEmail", email);
	}

	public void setKey(MemberDTO dto) {
		sqlSession.insert("login.setKey", dto);
	}

	public int join(MemberDTO join) {
		return sqlSession.insert("login.join", join);
	}

	public int idDuplicationChecck(String enteredId) {
		return sqlSession.selectOne("login.idDuplicationChecck", enteredId);
	}





}
