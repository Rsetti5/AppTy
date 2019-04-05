package com.scidap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scidap.model.Menu;

public interface MenuRepository extends JpaRepository<Menu,Long> {
	
	List<Menu> findByMerchantRestaurantIdOrderByCategoryAsc(Long merchantId);

}
