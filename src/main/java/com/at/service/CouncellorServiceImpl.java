package com.at.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.dto.DashboardResponse;
import com.at.entity.Councellor;
import com.at.entity.Enquiry;
import com.at.repositories.CouncellorRepo;
import com.at.repositories.EnquiryRepo;



@Service
public class CouncellorServiceImpl implements CouncellorService{

	@Autowired
	private CouncellorRepo crepo;
	
	@Autowired   
	private EnquiryRepo erepo;
	
	public CouncellorServiceImpl(CouncellorRepo crepo, EnquiryRepo erepo)
	{
		this.crepo = crepo;
		this.erepo=erepo;
	}
	
	@Override
	public Councellor login(String email, String password) {
		
	Optional<Councellor> opt=crepo.findByEmailAndPassword(email, password);
		
		if(opt.isPresent())
		{
		return opt.get();
		}
		
		return null;
	}

	
	@Override
	public boolean register(Councellor councellor) 
	{
		if(crepo.existsByEmail(councellor.getEmail()))
		{
		return false;
		} 
		
		crepo.save(councellor);
		return true;
		
	}
	 

	@Override
	public DashboardResponse getDashboardInfo(Integer councellorId) {
		 
		
		DashboardResponse response =new DashboardResponse();
		
		List<Enquiry> enqList=erepo.getEnquiryByCouncellorId(councellorId);
		
		int totalEnq=enqList.size();
		
		int enrolledEnqs=enqList.stream()
				.filter(e ->e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList())
				.size();
		
		int lostEnquiry=enqList.stream()
				.filter(e -> e.getEnqStatus().equals("Lost"))
				.collect(Collectors.toList())
				.size();
		
		int openEnquiry=enqList.stream()
				.filter(e -> e.getEnqStatus().equals("Open"))
				.collect(Collectors.toList())
				.size();
		
		response.setLostEnqs(lostEnquiry);
		response.setOpenEnqs(openEnquiry);
		response.setEnrolledEnqs(enrolledEnqs);
		response.setTotalEnqs(totalEnq);
		
		return response;
	}

}
