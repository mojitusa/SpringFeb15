package org.ryuuzakiumi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	private int loginFlag, mLoginAttemptCount;
	private String id, pw, mname;
}
