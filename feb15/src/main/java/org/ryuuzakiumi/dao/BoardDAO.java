package org.ryuuzakiumi.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.CommentDTO;
import org.ryuuzakiumi.dto.WriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> boardList(int currentPageNo){
		return sqlSession.selectList("board.boardList", currentPageNo);
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

	public int totalRecordCount() {
		return sqlSession.selectOne("board.totalRecordCount");
	}

	public int deleteComment(CommentDTO dto) {
		return sqlSession.update("board.deleteComment", dto);
	}

	public int likeUp(CommentDTO dto) {
		return sqlSession.update("board.likeUp", dto);
	}


}
