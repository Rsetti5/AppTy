package com.scidap.controller;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scidap.utils.Utility;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@GetMapping("/merchants/{image_id}")
	public String getMerchantImage(@PathVariable(value="image_id") Long imageId) {
		String path = System.getProperty("user.dir")+"/src/main/resources/merchants/logos/"+imageId+".jpg";
		File file = new File(path);
		return Utility.encodeFileToBase64Binary(file);
	}
	
	@GetMapping("/items/{image_id}")
	public String getItemImage(@PathVariable(value="image_id") Long imageId) {
		String path = System.getProperty("user.dir")+"/src/main/resources/merchants/items/"+imageId+".jpg";
		File file = new File(path);
		return Utility.encodeFileToBase64Binary(file);
	}
}
