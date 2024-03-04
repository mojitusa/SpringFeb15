package org.ryuuzakiumi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.SearchDTO;
import org.ryuuzakiumi.service.AdminService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

//administrator = admin
//root

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	@Autowired
	private Util util;
	
	
	@GetMapping() // url경로
	public String index2() {
		return "redirect:/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/index/") // url경로
	public String index3() {
		return "redirect:/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/index") // url경로
	public String index() {
		return "/admin/index";  // 폴더/ 파일
	}
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "admin/join";
	}
	
	@GetMapping("/board")
	public String board(
			@RequestParam(name="pageNo", defaultValue = "1") String pageNo, 
			@RequestParam(name="perPage", defaultValue = "1", required = false) String perPage,
			@RequestParam(name="searchTitle", required = false) String searchTitle,
			@RequestParam(name="search", required = false) String search,
			Model model) {
		//페이징 + 검색 + 한 화면에 보이는 게시글 수 변경
		
		
		//전체 글 수에도 DTO 보내기
		SearchDTO searchDTO  = new SearchDTO();
		searchDTO.setSearchTitle(searchTitle);
		searchDTO.setSearch(search);
		
		//전체 글 수 뽑기
		int totalRecordCount = adminService.totalRecordCount(searchDTO);
		
		//전자정부 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(util.str2Int(pageNo));
		paginationInfo.setRecordCountPerPage(util.str2Int(perPage) * 10); //한 페이지 보이는 글 수
		//10 20 30 40 50 100
		paginationInfo.setPageSize(10);
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		//검색에서 사용할 값 추가
		searchDTO.setPageNo(paginationInfo.getFirstRecordIndex());
		searchDTO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<BoardDTO> list = adminService.boardList(searchDTO);
		model.addAttribute("list", list);
		model.addAttribute("paginationInfo",paginationInfo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("perPage", perPage);
		model.addAttribute("searchTitle", searchTitle);
		model.addAttribute("search", search);
		
		
		return "admin/board";
	}
	
	//20240304 삭제
	@GetMapping("postDel")
	public String postDel(@RequestParam(name="no", required = false) int no,  
	@RequestParam(name="del", required = false) String del) {
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setBoard_no(no);
		boardDTO.setBoard_del(del);
		
		int result = adminService.postDel(boardDTO);
		
		return "redirect:/admin/board";
	}

}
