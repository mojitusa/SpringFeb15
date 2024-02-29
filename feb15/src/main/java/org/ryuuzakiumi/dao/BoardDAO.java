package org.ryuuzakiumi.dao;

import java.util.List;

import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.CommentDTO;
import org.ryuuzakiumi.dto.SearchDTO;
import org.ryuuzakiumi.dto.WriteDTO;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO extends AbstractDAO {
	
	public List<BoardDTO> boardList(SearchDTO searchDTO){
		return sqlSession.selectList("board.boardList", searchDTO);
	}

	public BoardDTO detail(int no) {
		return sqlSession.selectOne("board.detail", no);
	}

	public int readFlag(BoardDTO dto) {
		return sqlSession.selectOne("board.readFlag", dto);
	}
	
	public void viewCountup(BoardDTO dto) {
		sqlSession.insert("board.viewCountUp", dto);
	}
	
	public int write(WriteDTO dto) {
		return sqlSession.insert("board.write", dto);
	}
	
	public int commentWrite(CommentDTO comment) {
		return sqlSession.insert("board.commentWrite", comment);
	}

	public List<CommentDTO> commentsList(int reNo) {
		return sqlSession.selectList("board.commentsList", reNo);
	}

	public int postDel(WriteDTO dto) {
		return sqlSession.update("board.postDel", dto);
	}

	public int totalRecordCount(String search) {
		return sqlSession.selectOne("board.totalRecordCount", search);
	}

	public int deleteComment(CommentDTO dto) {
		return sqlSession.update("board.deleteComment", dto);
	}

	public int likeUp(CommentDTO dto) {
		return sqlSession.update("board.likeUp", dto);
	}


}