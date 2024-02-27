package org.ryuuzakiumi.dao;

import java.util.List;

import org.ryuuzakiumi.dto.NoticeDTO;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO extends AbstractDAO {

	public List<NoticeDTO> noticeList(int pageNo) {
		return sqlSession.selectList("notice.noticeList", pageNo);
	}

	public int noticeWrite(NoticeDTO noticeDTO) {
		return sqlSession.insert("notice.noticeWrite", noticeDTO);
	}

	public NoticeDTO detail(int no) {
		return sqlSession.selectOne("notice.detail", no);
	}

	public int noticeDel(int no) {
		return sqlSession.update("notice.noticeDel", no);
	}

	public int totalRecordCount() {
		return sqlSession.selectOne("notice.totalRecordCount");
	}

}
