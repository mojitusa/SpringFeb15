package org.ryuuzakiumi.service;

import org.apache.commons.mail.EmailException;
import org.ryuuzakiumi.dao.RestDAO;
import org.ryuuzakiumi.dto.MemberDTO;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestService extends AbstractService{
	
	@Autowired
	private RestDAO restDAO;

	public String getEmail(String email) {
		return restDAO.getEmail(email);
	}	

	public int sendEmail() throws EmailException {
		if (util.getSession().getAttribute("mid") != null) {
			//메일 발송 + key는 DB에 저장
			String email = getEmail((String)util.getSession().getAttribute("mid"));
			String key = util.createKey();
			
			MemberDTO dto = new MemberDTO();
			dto.setMemail(email);
			dto.setMkey(key);
			dto.setMid((String)util.getSession().getAttribute("mid"));
			
			restDAO.setKey(dto); //db에 키 저장
			
			//util.sendEmail(email, key);	
			
			return 1;
		} else {
			return 0;
		}
	}

}
