package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dto.NoticeDTO;

public interface NoticeService {
	
	public List<NoticeDTO> noticeList(int no);
	
	public NoticeDTO detail(int no);
	
	public int noticeWrite(NoticeDTO noticeDTO);
	
	public int noticeDel(int no);

	public int noticeUpdate(NoticeDTO noticeDTO);

	public int totalRecordCount();
	
}
