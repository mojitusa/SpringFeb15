package org.ryuuzakiumi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.SearchDTO;
import org.ryuuzakiumi.service.BoardService;
import org.ryuuzakiumi.service.RestService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@RestController
public class RestFullController {
	//ajax API들은 여기에 모아둠
	//@responsebody가 필요 없다.
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private RestService restService;
	
	@Autowired
	private Util util;

	@PostMapping("/restDetail")
	public BoardDTO restDetail(@Param("no") int no) {
		BoardDTO detail = boardService.detail(no); 
		
		return detail;
	}
	
	@PostMapping("/emailAuth")
	public int emailAuth() throws EmailException {
			return restService.sendEmail();
	}
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestParam("id") String id) {
		int result = restService.idCheck(id);
		// {key : value, key2 : value, key3 : value } 
		// {key : value, a:{a:b} }
		
		// result : {count : 0/1}
		
		/*
		 * Map<String, Object> resultMap = new HashMap<String, Object>();
		 * resultMap.put("count", result);
		 * 맵사용 시
		 */
		
		//JsonObject 이용 시
		JSONObject json = new JSONObject();
		json.put("count", result);
		
		return json.toString();
	}
	
	//게시판을 json으로 출력해 주는 api
	@GetMapping("/jsonBoard")
	public String jsonBoard(
			@RequestParam("pageNo") String pageNo, 
			@RequestParam(value = "search", required = false) String search) {
		
		//pageNo가 오지 않는다면
		int currentPageNo = 1;
		int intNo = util.str2Int(pageNo);
		if (intNo != 0) {  //여기 나중에 수정
			currentPageNo = intNo;
		}
		
		//전체 글 수
		int totalRecordCount = boardService.totalRecordCount(search);
		
		//pagination
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totalRecordCount);//전체 게시물 개수
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPageNo(paginationInfo.getFirstRecordIndex());
		searchDTO.setSearch(search);
		
		/*
		 * List<BoardDTO> list =
		 * boardService.boardList(paginationInfo.getFirstRecordIndex());
		 */
		
		//JSON
		JSONObject jsonList = new JSONObject();
		/* jsonList.put("list", list); */
		jsonList.put("paginationInfo", paginationInfo);
		jsonList.put("pageNo", pageNo);
		
		return jsonList.toString();
	}
}
