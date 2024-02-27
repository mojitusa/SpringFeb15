package org.ryuuzakiumi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.ryuuzakiumi.dto.NoticeDTO;
import org.ryuuzakiumi.service.NoticeService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class NoticeController {

	@Autowired
	private Util util;

	@Resource(name = "noticeService")
	private NoticeService noticeService;

	// 2024-02-27 요구사항 확인 psd

	@GetMapping("/notice")
	public String notice(
			@RequestParam(value = "pageNo", defaultValue = "1", required = false)
			String no, Model model) {
		
		int currentPageNo = 1;
		int intNo = util.str2Int(no);
		if (intNo != 0) {
			currentPageNo = intNo;
		}
		
		int totalRecordCount = noticeService.totalRecordCount();

		//Pagination
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totalRecordCount);//전체 게시물 개수		
		
		List<NoticeDTO> list = noticeService.noticeList(paginationInfo.getFirstRecordIndex());
		model.addAttribute("list", list);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "notice"; // ....../views/notice.jsp
	}

	// 공지 글쓰기 -> admin 관리자 화면에서
	@GetMapping("/admin/noticeWrite")
	public String noticeWrite() {
		return "admin/noticeWrite"; // ....../views/admin/noticewrite.jsp
	}

	@PostMapping("/admin/noticeWrite")
	public String noticeWrite(NoticeDTO noticeDTO) {
		int result = noticeService.noticeWrite(noticeDTO);

		if (result == 0) {
			return "redirect:/error";
		} else {
			return "redirect:/notice";
		}
	}

	@GetMapping("/noticedetail")
	public String detail(@RequestParam(value = "no", defaultValue = "0", required = true) String no, Model model) {
		int intNo = util.str2Int(no);

		if (intNo != 0) {
			NoticeDTO detail = noticeService.detail(intNo);
			if (detail.getNno() != 0) {
				model.addAttribute("detail", detail);
				return "noticedetail";
			} else {
				return "redirect:/error";
			}
		} else {
			return "redirect:/error";
		}
	}
	
	//noticeDel
	@GetMapping("/noticeDel{no}")
	public String noticeDel(@PathVariable("no") int no) {
		//System.out.println("@PathVariable : " + no);
		noticeService.noticeDel(no);
		
		return "redirect:/notice";
	}

}
