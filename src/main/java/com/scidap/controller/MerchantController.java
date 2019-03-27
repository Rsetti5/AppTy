package com.scidap.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scidap.model.Menu;
import com.scidap.model.Merchant;
import com.scidap.repository.MenuRepository;
import com.scidap.repository.MerchantRepository;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
	
	@Autowired
	private MerchantRepository merchantRep;
	
	@Autowired
	private MenuRepository menuRep;
	
	@GetMapping("/get/{merchant_id}")
	public Merchant getMerchantFromId(@PathVariable(value="merchant_id") Long merchant_id) throws Exception {
		return merchantRep.findById(merchant_id).orElseThrow(()-> new Exception());
	}
	
	@GetMapping("/get")
	public List<Merchant> getAllMerchants() {
		return merchantRep.findAll();
	}

	@PostMapping("/create")
	public Merchant createMerchant(@Valid @RequestBody Merchant merchant) {
		String merchant_logo = merchant.getLogo();
		byte[] data = Base64.getDecoder().decode(merchant_logo);
		String path = System.getProperty("user.dir")+"/src/main/resources/merchants/logos/";
		File file = new File(path+merchant.getName()+".jpg");		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try( OutputStream stream = new FileOutputStream(file,true) ) {
			stream.write(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
		merchant.setLogo(path+merchant.getName()+".jpg");
		merchant = merchantRep.save(merchant);
		File newFile = new File(path+merchant.getId()+".jpg");
		file.renameTo(newFile);
		merchant.setLogo(path+merchant.getId()+".jpg");
		return merchantRep.save(merchant);
	}
	
	@GetMapping("/{merchant_id}/menu")
	public List<Menu> getMenuFromMerchantId(@PathVariable(value="merchant_id") Long merchantId){
		return menuRep.findByMerchantIdOrderByCategoryAsc(merchantId);
	}
	
	@PostMapping("/{merchant_id}/menu/create")
	public Menu createMenuForMerchant(@PathVariable(value="merchant_id") Long merchantId, @Valid @RequestBody Menu menu){
		menu.setMerchantId(merchantId);
		byte[] data = Base64.getDecoder().decode(menu.getImageUrl());
		String path = System.getProperty("user.dir")+"/src/main/resources/merchants/items/";
		File file = new File(path+menu.getName()+".jpg");		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try( OutputStream stream = new FileOutputStream(file,true) ) {
			stream.write(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
		menu.setImageUrl(path+menu.getName()+".jpg");
		return menuRep.save(menu);
	}
}
