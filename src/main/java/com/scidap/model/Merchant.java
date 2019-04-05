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
@Table(name="merchant_details")
public class Merchant {
	@Id
	@Column(name="merchant_restaurant_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long merchantRestaurantId;
	
	public long getMerchantRestaurantId() {
		return merchantRestaurantId;
	}

	public void setMerchantRestaurantId(long merchantRestaurantId) {
		this.merchantRestaurantId = merchantRestaurantId;
	}

	@Column(name="merchant_id")
    private long merchant_id;

	@Column(name="merchant_name")
	private String name;
	
	@Column(name="merchant_location")
	private String location;
	
	@Column(name="merchant_email")
	private String email;
	
	@Column(name="merchant_logo")
	private String logo;
	
	@Column(name="merchant_contact_number")
	private String contact_number;
	
	@Column(name="merchant_address")
	private String address;
	
	@Column(name="merchant_menu_id")
	private long menu_id;
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name="creation_date")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_date;
	
	@Column(name="modified_date")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_date;
	
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}


}
