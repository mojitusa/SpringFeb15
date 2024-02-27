package org.ryuuzakiumi.service;

import java.util.List;

import org.ryuuzakiumi.dto.GalleryDTO;

public interface GalleryService {
	
	public int galleryInsert(GalleryDTO dto);
	//강제한다.
	
	public List<GalleryDTO> galleryList();
	
	public GalleryDTO galleryDetail(GalleryDTO dto);
	
	
	

}
