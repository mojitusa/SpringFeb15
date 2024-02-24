package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dao.GalleryDAO;
import org.ryuuzakiumi.dto.GalleryDTO;
import org.ryuuzakiumi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

	@Autowired
	private GalleryDAO galleryDAO;

	@Autowired
	private Util util;

	public int galleryInsert(GalleryDTO dto) {

		if (util.getSession().getAttribute("mid") != null) {
			dto.setMid((String) util.getSession().getAttribute("mid"));
			return galleryDAO.galleryInsert(dto);
		} else {
			return 0;
		}
	}

	public List<GalleryDTO> galleryList() {
		return galleryDAO.galleryList();
	}

}
