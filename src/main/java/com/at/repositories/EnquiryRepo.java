package com.at.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.at.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

	@Query(value= "select * from enquiry_tbl where councellor_id=:councellorId", nativeQuery=true)
	public List<Enquiry> getEnquiryByCouncellorId(Integer councellorId);
}
