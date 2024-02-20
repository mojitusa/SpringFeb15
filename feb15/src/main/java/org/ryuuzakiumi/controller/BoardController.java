package org.ryuuzakiumi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ryuuzakiumi.dto.BoardDTO;
import org.ryuuzakiumi.dto.CommentDTO;
import org.ryuuzakiumi.dto.WriteDTO;
import org.ryuuzakiumi.service.BoardService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private Util util;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	//페이징 추가하기 2024-02-20 psd
	@GetMapping({"/board", "/"})
	public String board(
		@RequestParam(value = "pageNo", defaultValue = "1", required = false) String no, Model model) {
		
		//no는 null
		
		//pageNo가 오지 않는다면
		int currentPageNo = 1;
		if (util.str2Int(no) == 0) {  //여기 나중에 수정
			currentPageNo = Integer.parseInt(no);
		}
		
		int totalRecordCount = boardService.totalRecordCount();
		
		//pagination
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totalRecordCount);//전체 게시물 개수
		
		List<BoardDTO> list = boardService.boardList(paginationInfo.getFirstRecordIndex());
		model.addAttribute("list", list);
		
		//페이징 관련 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어 준다.
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "board";
		
	}

	// 2024-02-15 ~ 2024-02-16
	@GetMapping("/detail")
	public String detail(@RequestParam(value = "no", defaultValue = "0", required = true) String no, Model model) {		
		//오는 매개변수 no 잡기
		//String no = request.getParameter("no");
		//System.out.println(util.str2Int(no)); //숫자면 숫자 문자면 0
		int reNo = util.str2Int(no);
		if(reNo != 0) {
			//0이 아님 - 정상 -> DB에 물어보기 -> 값 가져오기 -> 붙이기
			//						무조건 S D 통과
			BoardDTO detail = boardService.detail(reNo);
			model.addAttribute("detail", detail);
			
			//2024-02-19 psd 댓글도 뽑기
			//System.out.println("댓글 수 : " + detail.getComment());
			if (detail.getComment() > 0) {
				List<CommentDTO> commentsList = boardService.commentsList(reNo);
				model.addAttribute("commentsList", commentsList);
				//페이지로 보내 주기
				
				System.out.println(commentsList.get(0).getMname());
			}
			
			return "detail";
			
		} else { // 0이라면 
		
			//return "error/error"; //에러 폴더/error.jsp
			return "redirect:/error"; //controller에 있는 error 매핑을 실행해
		}
	}
	
	//글쓰기 2024-02-16
	@PostMapping("/write")	//내용 + 제목 -> DB에 저장 -> 보드로
	public String write(WriteDTO dto, HttpServletRequest request) {
		
		int result = boardService.write(dto, request);
		// 0(문제 발생) 1(정상)
		//추후 세션 관련 작업을 더 해야 합니다.
		
		if (result == 1) {
			return "redirect:/detail?no="+dto.getBoard_no();
		} else {
			return "return:/error";
		}
	}
	
	//댓글 쓰기 2024-02-19 psd == 글번호 no, 댓글내용 comment, 글쓴이
	@PostMapping("/commentWrite")
	public String commentWrite(CommentDTO comment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		comment.setMid((String)session.getAttribute("mid"));
		int result = boardService.commentWrite(comment);
		System.out.println("댓글쓰기 결과" + result);
		return "redirect:/detail?no="+comment.getNo();
	}
	
	@PostMapping("postDel")
	public String postDel(@RequestParam("no") int no) {
		//System.out.println("no: " + no);
		int result = boardService.postDel(no);
		System.out.println("result : " + result);
		return "redirect:/board";
	}
}

