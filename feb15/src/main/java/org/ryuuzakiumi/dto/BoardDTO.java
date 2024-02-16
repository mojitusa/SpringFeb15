package org.ryuuzakiumi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private int board_no, board_count, comment;
	private String board_title, board_content, board_write, board_date;

}
