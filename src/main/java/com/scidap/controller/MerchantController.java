package com.scidap.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scidap.model.Menu;
import com.scidap.model.Merchant;
import com.scidap.model.MerchantItemDetails;
import com.scidap.repository.MenuRepository;
import com.scidap.repository.MerchantItemDetailsRepository;
import com.scidap.repository.MerchantRepository;
import com.scidap.utils.ParsingUtil;

@RestController
@RequestMapping("/api/merchants/restaurants")
public class MerchantController {
	
	@Autowired
	private MerchantRepository merchantRep;
	
	@Autowired
	private MenuRepository menuRep;
	
	@Autowired
	MerchantItemDetailsRepository merchantDetailRep;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/get/{merchant_restaurant_id}")
	public Merchant getMerchantFromId(@PathVariable(value="merchant_restaurant_id") Long merchant_restaurant_id) throws Exception {
		return merchantRep.findById(merchant_restaurant_id).orElseThrow(()-> new Exception());
	}
	
	@GetMapping("/get")
	public List<Merchant> getAllMerchants() {
		return merchantRep.findAll();
	}

	@PostMapping("/create")
	public Merchant createMerchant(@Valid @RequestBody Merchant merchant) {
		String merchant_logo = merchant.getLogo();
		if(!("".equalsIgnoreCase(merchant_logo))) {
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
			String urlPath= env.getProperty("imageUrl");
			merchant.setLogo(urlPath+"/api/images/merchants/"+merchant.getId());
		}	
		return merchantRep.save(merchant);
	}
	
	@PostMapping("/update")
	public Merchant updateMerchant(@Valid @RequestBody Merchant merchant) {
		Merchant old_merchant =null;
		try {
			old_merchant = merchantRep.getOne(merchant.getId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		String merchant_logo = merchant.getLogo();
		if(!("".equalsIgnoreCase(merchant_logo))) {
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
			String urlPath= env.getProperty("imageUrl");
			merchant.setLogo(urlPath+"/api/images/merchants/"+merchant.getId());
		}		
		old_merchant= ParsingUtil.parseMerchantDetails(merchant, old_merchant);
		return merchantRep.save(old_merchant);
	}
	
	@GetMapping("/{merchant_restaurant_id}/menu")
	public List<MerchantItemDetails> getMenuFromMerchantId(@PathVariable(value="merchant_restaurant_id") Long merchant_restaurant_id){
		return merchantDetailRep.findByMerchantIdOrderByCategoryAsc(merchant_restaurant_id);
	}
	
	@PostMapping("/{merchant_restaurant_id}/menu/create")
	public Menu createMenuForMerchant(@PathVariable(value="merchant_restaurant_id") Long merchant_restaurant_id, @Valid @RequestBody Menu menu){
		menu.setMerchantId(merchant_restaurant_id);
		String image = menu.getImageUrl();
		if(!("".equalsIgnoreCase(image))) {
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
			menu = menuRep.save(menu);
			File newFile = new File(path+menu.getItemId()+".jpg");
			file.renameTo(newFile);
			String urlPath= env.getProperty("imageUrl");
			menu.setImageUrl(urlPath+"/api/images/items/"+menu.getItemId());
		}
		return menuRep.save(menu);
	}
	
	@PostMapping("/{merchant_restaurant_id}/menu/update")
	public Menu updateMenuForMerchant(@PathVariable(value="merchant_restaurant_id") Long merchant_restaurant_id, @Valid @RequestBody Menu menu){
		Menu old_menu =null;
		try {
			old_menu = menuRep.getOne(menu.getItemId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		menu.setMerchantId(merchant_restaurant_id);
		String image = menu.getImageUrl();
		if(!("".equalsIgnoreCase(image))) {
			byte[] data = Base64.getDecoder().decode(image);
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
			String urlPath= env.getProperty("imageUrl");
			menu.setImageUrl(urlPath+"/api/images/items/"+menu.getItemId());
		}
		old_menu=ParsingUtil.parseMenuDetails(menu, old_menu);
		return menuRep.save(old_menu);
	}
}
