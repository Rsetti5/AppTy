package com.scidap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scidap.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Long> {
 
}
