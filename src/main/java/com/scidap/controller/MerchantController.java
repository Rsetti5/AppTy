package com.scidap.controller;

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
		return merchantRep.save(merchant);
	}
	
	@GetMapping("/{merchant_id}/menu")
	public List<Menu> getMenuFromMerchantId(@PathVariable(value="merchant_id") Long merchantId){
		return menuRep.findByMerchantIdOrderByCategoryAsc(merchantId);
	}
	
	@PostMapping("/{merchant_id}/menu/create")
	public Menu createMenuForMerchant(@PathVariable(value="merchant_id") Long merchantId, @Valid @RequestBody Menu menu){
		menu.setMerchantId(merchantId);
		return menuRep.save(menu);
	}
}
