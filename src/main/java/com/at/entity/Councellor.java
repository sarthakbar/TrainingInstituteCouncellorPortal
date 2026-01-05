package com.at.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="councellor_tbl")
@Data
public class Councellor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer councellorId;
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private Long phoneNo;
	@CreationTimestamp
	private LocalDate created_date;
	@UpdateTimestamp
	private LocalDate updated_date;
	
}
