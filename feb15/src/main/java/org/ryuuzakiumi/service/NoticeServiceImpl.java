package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dao.NoticeDAO;
import org.ryuuzakiumi.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeServiceImpl extends AbstractService implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public List<NoticeDTO> noticeList(int no) {
		return noticeDAO.noticeList(no);
	}

	@Override
	public NoticeDTO detail(int no) {
		return noticeDAO.detail(no);
	}

	@Override
	public int noticeWrite(NoticeDTO noticeDTO) {
		return noticeDAO.noticeWrite(noticeDTO);
	}

	@Override
	public int noticeDel(int no) {
		return noticeDAO.noticeDel(no);
	}

	@Override
	public int noticeUpdate(NoticeDTO noticeDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalRecordCount() {
		return noticeDAO.totalRecordCount();
	}

}
