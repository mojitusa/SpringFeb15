package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dao.GalleryDAO;
import org.ryuuzakiumi.dto.GalleryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("galleryService")
public class GalleryServiceImpl extends AbstractService implements GalleryService{

	@Autowired
	private GalleryDAO galleryDAO;

	@Override
	public int galleryInsert(GalleryDTO dto) {

		if (util.getSession().getAttribute("mid") != null) {
			dto.setMid((String) util.getSession().getAttribute("mid"));
			return galleryDAO.galleryInsert(dto);
		} else {
			return 0;
		}
	}

	@Override
	public List<GalleryDTO> galleryList() {
		return galleryDAO.galleryList();
	}

	@Override
	public GalleryDTO galleryDetail(GalleryDTO dto) {
		
		return galleryDAO.galleryDetail(dto);
	}

}
