package org.ryuuzakiumi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	int mno, mgrade, mloginAttemptCount;
	String mid, mpw, mname, mdate, memail, mkey;

}
