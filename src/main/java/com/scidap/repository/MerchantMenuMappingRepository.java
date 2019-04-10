package com.scidap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scidap.model.MerchantMenuMapping;

public interface MerchantMenuMappingRepository extends JpaRepository<MerchantMenuMapping,Long>{

	List<MerchantMenuMapping> findByItemIdAndMerchantId(long itemId, long merchantId);
}
