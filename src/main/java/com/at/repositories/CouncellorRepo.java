package com.at.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.at.entity.Councellor;

public interface CouncellorRepo extends JpaRepository<Councellor,Integer>{

	//Optional<Councellor> findByEmail(String email);
	boolean existsByEmail(String email);
	Optional<Councellor> findByEmailAndPassword(String email,String password);

}
