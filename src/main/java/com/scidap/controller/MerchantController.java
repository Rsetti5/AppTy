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
import com.scidap.model.MerchantMenuMapping;
import com.scidap.repository.MenuRepository;
import com.scidap.repository.MerchantItemDetailsRepository;
import com.scidap.repository.MerchantMenuMappingRepository;
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
	MerchantMenuMappingRepository merchantMenuMappingRep;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/get/{merchant_id}")
	public Merchant getMerchantFromId(@PathVariable(value="merchant_id") Long merchant_id) throws Exception {
		return merchantRep.findByMerchantId(merchant_id);
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
			File newFile = new File(path+merchant.getMerchantRestaurantId()+".jpg");
			file.renameTo(newFile);
			String urlPath= env.getProperty("imageUrl");
			merchant.setLogo(urlPath+"/api/images/merchants/"+merchant.getMerchantRestaurantId()+".jpg");
		}	
		return merchantRep.save(merchant);
	}
	
	@PostMapping("/update")
	public Merchant updateMerchant(@Valid @RequestBody Merchant merchant) {
		Merchant old_merchant =null;
		try {
			old_merchant = merchantRep.getOne(merchant.getMerchantRestaurantId());
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
			merchant.setLogo(urlPath+"/api/images/merchants/"+merchant.getMerchantRestaurantId()+".jpg");
		}		
		old_merchant= ParsingUtil.parseMerchantDetails(merchant, old_merchant);
		return merchantRep.save(old_merchant);
	}
	
	@GetMapping("/{merchant_id}/menu/get")
	public List<MerchantItemDetails> getMenuFromMerchantId(@PathVariable(value="merchant_id") Long merchant_id){
		return merchantDetailRep.findByMerchantIdOrderByCategoryAsc(merchant_id);
	}
	
	@PostMapping("/{merchant_id}/menu/create")
	public Menu createMenuForMerchant(@PathVariable(value="merchant_id") Long merchant_id, @Valid @RequestBody Menu menu){
		String image = menu.getImageUrl();
		menu.setMerchantId(merchant_id);
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
			menu.setImageUrl(urlPath+"/api/images/items/"+menu.getItemId()+".jpg");
		}
		MerchantMenuMapping menuMap = new MerchantMenuMapping();
		menuMap.setItemId(menu.getItemId());
		menuMap.setMerchantId(merchant_id);
		menuMap.setCost(menu.getCost());
		menuMap.setSellingPrice(menu.getSellingPrice());
		menuMap.setDiscount(menu.getDiscount());
		merchantMenuMappingRep.save(menuMap);
		return menuRep.save(menu);
	}
	
	@PostMapping("/{merchant_id}/menu/update")
	public Menu updateMenuForMerchant(@PathVariable(value="merchant_id") Long merchant_id, @Valid @RequestBody Menu menu){
		Menu old_menu =null;
		try {
			old_menu = menuRep.getOne(menu.getItemId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
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
			menu.setImageUrl(urlPath+"/api/images/items/"+menu.getItemId()+".jpg");
		}
		old_menu=ParsingUtil.parseMenuDetails(menu, old_menu);
		List<MerchantMenuMapping> menuMapList = merchantMenuMappingRep.findByItemIdAndMerchantId(menu.getItemId(),merchant_id);
		MerchantMenuMapping menuMap = menuMapList.get(0);
		menuMap.setCost(menu.getCost());
		menuMap.setSellingPrice(menu.getSellingPrice());
		menuMap.setDiscount(menu.getDiscount());
		merchantMenuMappingRep.save(menuMap);
		return menuRep.save(old_menu);
	}
}
