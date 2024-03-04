package org.ryuuzakiumi.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends AbstractDAO{
	
	@Autowired
	private SqlSession sqlsession;

	public List<BoardDTO> boardList(SearchDTO searchDTO) {
		return sqlsession.selectList("admin.boardList", searchDTO);
	}

	public int totalRecordCount(SearchDTO searchDTO) {
		return sqlsession.selectOne("admin.totalRecordCount", searchDTO);
	}

	public int postDel(BoardDTO boardDTO) {
		return sqlsession.update("admin.postDel", boardDTO);
	}

}
