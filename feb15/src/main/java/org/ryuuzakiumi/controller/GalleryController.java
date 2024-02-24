package org.ryuuzakiumi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ryuuzakiumi.dto.GalleryDTO;
import org.ryuuzakiumi.service.GalleryService;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private Util util; 
	
	@GetMapping("/gallery")
	public String gallery(Model model) {
		List<GalleryDTO> list = galleryService.galleryList();
		model.addAttribute("list", list);
		return "galleryinsert";
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

}
