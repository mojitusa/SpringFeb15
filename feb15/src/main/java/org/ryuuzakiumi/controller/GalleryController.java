package org.ryuuzakiumi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ryuuzakiumi.dto.GalleryDTO;
import org.ryuuzakiumi.service.GalleryService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
	
	@Resource (name="galleryService")
	private GalleryService galleryService;
	
	@Autowired
	private Util util; 
	
	@GetMapping("/gallery")
	public String gallery(Model model) {
		List<GalleryDTO> list = galleryService.galleryList();
		model.addAttribute("list", list);
		return "gallery";
	}
	
	@GetMapping("/galleryinsert")
	public String galleryInsert() {
		return "galleryinsert";
	}
	
	@PostMapping("/galleryInsert")
	public String galleryInsert(GalleryDTO dto, @RequestParam("uploadingFile") MultipartFile upFile, HttpServletRequest request) {
		
		System.out.println(dto.getGtitle());
		System.out.println(dto.getGcontent());
		System.out.println(upFile.getOriginalFilename());
		
		
		//파일 업로드 -> util
		String newFileName = util.fileUpload(upFile);
		
		
		dto.setGfile(newFileName);  // UUID+있어야 함

		int result = galleryService.galleryInsert(dto);
		
		return "redirect:/gallery";
	}
	
	//galleryDrtail
	//2024-02-26 요구사항 확인 psd
	@GetMapping("/galleryDetail@{no}")
	public String galleryDetail(@PathVariable("no") String no) {
		System.out.println("경로 |: " + no);
		return "galleryDetail";
	}
	
	@GetMapping("/gallery/{gno}")
	public String galleryDetail(@PathVariable int gno, Model model) {
		
	    // gno를 사용하여 필요한 작업을 수행
		GalleryDTO dto = new GalleryDTO();
		dto.setGno(gno);
		GalleryDTO detail = galleryService.galleryDetail(dto);
		
		System.out.println("제목 : " + detail.getGtitle());
		
		// 모델에 데이터를 추가하고 해당 뷰로 반환
		model.addAttribute("detail", detail);
		
	    return "gallerydetail"; // 예시로 뷰 이름은 "galleryDetail"로 설정
	}
	

}
