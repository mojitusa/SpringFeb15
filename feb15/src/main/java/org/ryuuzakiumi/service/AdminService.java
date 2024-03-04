package org.ryuuzakiumi.service;

import java.util.HashMap;
import java.util.List;

import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.SearchDTO;

public interface AdminService {

	List<BoardDTO> boardList(SearchDTO searchDTO);

	int totalRecordCount(SearchDTO searchDTO);

	int postDel(BoardDTO boardDTO);
	

}