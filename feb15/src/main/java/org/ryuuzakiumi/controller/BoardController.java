package org.ryuuzakiumi.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

	@GetMapping({"/board", "/"})
	public String board(Model model) {
		List<BoardDTO> list = boardService.boardList();
		model.addAttribute("list", list);
		
		return "board";
		
	}

	// 2024-02-15 ~ 2024-02-16
	@GetMapping("/detail")
	public String detail(@Param("no") String no, Model model) {		
		//오는 매개변수 no 잡기
		//String no = request.getParameter("no");
		//System.out.println(util.str2Int(no)); //숫자면 숫자 문자면 0
		int reNo = util.str2Int(no);
		if(reNo != 0) {
			//0이 아님 - 정상 -> DB에 물어보기 -> 값 가져오기 -> 붙이기
			//						무조건 S D 통과
			BoardDTO detail = boardService.detail(reNo);
			model.addAttribute("detail", detail);
			return "detail";
			
		} else { // 0이라면 
		
			//return "error/error"; //에러 폴더/error.jsp
			return "redirect:/error"; //controller에 있는 error 매핑을 실행해
		}
	}
	
	//글쓰기 2024-02-16
	@PostMapping("/write")	//내용 + 제목 -> DB에 저장 -> 보드로
	public String write(WriteDTO dto) {
		
		int result = boardService.write(dto);
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
	public String commentWrite(CommentDTO comment) {
		int result = boardService.commentWrite(comment);
		System.out.println("댓글쓰기 결과" + result);
		return "redirect:/detail";
	}
}

