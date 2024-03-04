package org.ryuuzakiumi.service;

import java.util.HashMap;
import java.util.List;

import org.ryuuzakiumi.dao.AdminDAO;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl extends AbstractService implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<BoardDTO> boardList(SearchDTO searchDTO) {
		return adminDAO.boardList(searchDTO);
	}

	@Override
	public int totalRecordCount(SearchDTO searchDTO) {
		return adminDAO.totalRecordCount(searchDTO);
	}

	@Override
	public int postDel(BoardDTO boardDTO ) {
		return adminDAO.postDel(boardDTO);
	}

	
	
}
