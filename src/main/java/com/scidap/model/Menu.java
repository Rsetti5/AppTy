package com.scidap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="item_details")
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="item_id")
	private Long itemId;
	
	@Column(name="merchant_restaurant_id")
	private Long merchantRestaurantId;
	
	public Long getMerchantRestaurantId() {
		return merchantRestaurantId;
	}

	public void setMerchantRestaurantId(Long merchantRestaurantId) {
		this.merchantRestaurantId = merchantRestaurantId;
	}

	@Column(name="item_category")
	private String category;
	
	@Column(name="item_name")
	private String name;
	
	@Column(name="item_cost")
	private double cost;
	
	@Column(name="item_discount")
	private double discount;
	
	@Column(name="item_selling_price")
	private double sellingPrice;
	
	@Column(name="item_cuisine_type")
	private String cuisineType;
	
	@Column(name="item_description")
	private String description;
	
	@Column(name="item_image")
	private String imageUrl;
	
	@Column(name="item_meal_type")
	private String mealType;
	
	@Column(name="creation_date")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="modified_date")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="modified_by")
	private String modifiedBy;

}
