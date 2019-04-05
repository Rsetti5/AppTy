package com.scidap.utils;

import com.scidap.model.Menu;
import com.scidap.model.Merchant;

public class ParsingUtil {
	
	public static Merchant parseMerchantDetails(Merchant old_merchant, Merchant new_merchant) {
		new_merchant.setName(old_merchant.getName());
		new_merchant.setAddress(old_merchant.getAddress());
		new_merchant.setContact_number(old_merchant.getContact_number());
		new_merchant.setLocation(old_merchant.getLocation());
		new_merchant.setLogo(old_merchant.getLogo());
		return new_merchant;
	}
	
	public static Menu parseMenuDetails(Menu old_menu, Menu new_menu) {
		new_menu.setCategory(old_menu.getCategory());
		new_menu.setCost(old_menu.getCost());
		new_menu.setCuisineType(old_menu.getCuisineType());
		new_menu.setDescription(old_menu.getDescription());
		new_menu.setDiscount(old_menu.getDiscount());
		new_menu.setImageUrl(old_menu.getImageUrl());
		new_menu.setMealType(old_menu.getMealType());
		new_menu.setMerchantRestaurantId(old_menu.getMerchantRestaurantId());
		new_menu.setName(old_menu.getName());
		new_menu.setSellingPrice(old_menu.getSellingPrice());
		return new_menu;
	}
}
