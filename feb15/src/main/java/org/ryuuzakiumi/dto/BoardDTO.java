package org.ryuuzakiumi.dto;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private int board_no, board_count, comment, today, mno;
	private String board_title, board_content, mname, mid, board_date, board_ip, board_del;
	private Timestamp bdate;
}
