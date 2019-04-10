package com.scidap.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@GetMapping(value ="/merchants/{image_id}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getMerchantImage(@PathVariable(value="image_id") Long imageId) throws IOException {
		String path = "/merchants/logos/"+imageId+".jpg";
		ClassPathResource imageFile = new ClassPathResource(path);
		//File file = new File(path);
		//return Utility.encodeFileToBase64Binary(file);
		return ResponseEntity.ok().contentLength(imageFile.contentLength()).contentType( MediaType.parseMediaType("image/jpeg")).body(new InputStreamResource(imageFile.getInputStream()));
	
	}
	
	@GetMapping(value="/items/{image_id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getItemImage(@PathVariable(value="image_id") Long imageId) throws IOException {
		String path = "/merchants/items/"+imageId+".jpg";
		ClassPathResource imageFile = new ClassPathResource(path);
		//File file = new File(path);
		return ResponseEntity.ok().contentLength(imageFile.contentLength()).contentType( MediaType.parseMediaType("image/jpeg")).body(new InputStreamResource(imageFile.getInputStream()));
	}
}
