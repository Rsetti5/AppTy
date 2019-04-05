package com.scidap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchant_item_mapping")
public class MerchantMenuMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="mappingId")
	private long mappingId;
	
	@Column(name="merchant_restaurant_id")
	private long merchantRestaurantId;
	
	public long getMappingId() {
		return mappingId;
	}

	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}

	public long getMerchantRestaurantId() {
		return merchantRestaurantId;
	}

	public void setMerchantRestaurantId(long merchantRestaurantId) {
		this.merchantRestaurantId = merchantRestaurantId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name="item_id")
	private long itemId;
	
	@Column(name="item_cost")
	private double cost;
	
	@Column(name="item_selling_price")
	private double sellingPrice;
	
	@Column(name="item_discount")
	private double discount;

}
