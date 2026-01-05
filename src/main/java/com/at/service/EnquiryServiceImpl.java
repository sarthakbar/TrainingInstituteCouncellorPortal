package com.at.service;

import java.util.List;

import org.springframework.data.domain.Example;

import com.at.dto.ViewEnquiryFilter;
import com.at.entity.Councellor;
import com.at.entity.Enquiry;
import com.at.repositories.CouncellorRepo;
import com.at.repositories.EnquiryRepo;

import io.micrometer.common.util.StringUtils;

   

public class EnquiryServiceImpl implements EnquiryService{

	 private CouncellorRepo crepo;
	 
	 private EnquiryRepo erepo;
	
	@Override
	public boolean addEnquiry(Enquiry enq, Integer councellorId) throws Exception{
		
		Councellor councellor= crepo.findById(councellorId).orElse(null);
	    if(councellor==null)
	    {
	    	throw new Exception("No councellor Found");
	    }
		
	    //associating councellor to enquiry
	    enq.setCouncellor(councellor);
	    Enquiry save= erepo.save(enq);
	    
	    if(save.getEnq_id()!=null)
	    {
	    	return true;
	    } 
	    
		return false;
		
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer councellorId) {
		
		List<Enquiry> list= erepo.getEnquiryByCouncellorId(councellorId);
		return list;
	}

	
	@Override
	public List<Enquiry> getEnquiriesWithfilter(ViewEnquiryFilter filterReq, Integer councellorId) {
		
		Enquiry enq= new Enquiry();
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode()))
		{
			enq.setClassMode(filterReq.getClassMode());
		}
		
		if(StringUtils.isNotEmpty(filterReq.getCourseName()))
		{
			enq.setCourseName(filterReq.getCourseName());
		}
		
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus()))
		{
			enq.setEnqStatus(filterReq.getEnqStatus());
		}
		
		Councellor c= crepo.findById(councellorId).orElse(null);
		enq.setCouncellor(c);
		
		Example<Enquiry> of= Example.of(enq);
		
		List<Enquiry> enqList= erepo.findAll(of)  ;
		
		return enqList;
	}
 
	  
	//ctr1ent
	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		return erepo.findById(enqId).orElse(null);
		
		}

}
