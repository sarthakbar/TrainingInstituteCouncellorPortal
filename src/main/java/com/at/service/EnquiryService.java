package com.at.service;

import java.util.List;

import com.at.dto.ViewEnquiryFilter;
import com.at.entity.Enquiry;

public interface EnquiryService {

	public boolean addEnquiry(Enquiry enq, Integer councellorId) throws Exception;
	public List<Enquiry> getAllEnquiries(Integer councellorId);
	public List<Enquiry> getEnquiriesWithfilter(ViewEnquiryFilter filterReq, Integer councellorId);
    public Enquiry getEnquiryById(Integer enqId);
}
