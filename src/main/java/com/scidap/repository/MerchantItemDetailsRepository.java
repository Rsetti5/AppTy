package com.scidap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scidap.model.MerchantItemDetails;

public interface MerchantItemDetailsRepository extends JpaRepository<MerchantItemDetails,Long>{
	
	List<MerchantItemDetails> findByMerchantIdOrderByCategoryAsc(Long merchantId);
}
