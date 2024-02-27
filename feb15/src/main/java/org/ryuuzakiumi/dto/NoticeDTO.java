package org.ryuuzakiumi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {
	int nno, ndel, nread, nlike;
	String ntitle, ncontent, ndate;
}
