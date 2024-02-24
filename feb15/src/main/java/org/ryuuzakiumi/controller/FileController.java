package org.ryuuzakiumi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class FileController {
	
	@GetMapping("/file")
	public String file() {
		return "file";
	}
	
	@PostMapping("/file")
	public String file(@RequestParam("file1") MultipartFile upFile, HttpServletRequest request) {
		System.out.println("파일 이름 : " + upFile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upFile.getSize());
		System.out.println("파일 타입 : " + upFile.getContentType());
		
		//경로
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root : " + root);
		String upfile = root + "resources\\upfile\\";
		System.out.println("upfile : " + upfile);
		
		
		
				
		//UUID 생성  ex)UUID-name.png   
		// 488d943e-04f4-409a-81e1-d783156a563c-name.png 
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		String newFileName = uuid.toString() + "-" + upFile.getOriginalFilename();
		System.out.println("새로 만들어진 파일 이름 : " + newFileName);
	
		
		//Real upLoad
		File f = new File(upfile, newFileName);
		try {
			upFile.transferTo(f);
			
			//섬네일 만들기
			FileOutputStream thumbnail = 
					new FileOutputStream(new File(upfile, "s_"+newFileName));
			Thumbnailator.createThumbnail(upFile.getInputStream(), thumbnail, 100, 100);
			thumbnail.close();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/file";
	}

}
