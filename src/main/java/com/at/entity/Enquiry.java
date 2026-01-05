package com.at.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="enquiry_tbl")
@Data
public class Enquiry {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enq_id;
	private String studentName;
	private Long studentPhno;
	private String courseName;
	private String classMode;
	private String enqStatus;
	@CreationTimestamp
	private LocalDateTime created_date;
	@UpdateTimestamp
	private LocalDateTime updated_date;
	
	@ManyToOne
	@JoinColumn(name="councellorId")
	private Councellor councellor;
}
