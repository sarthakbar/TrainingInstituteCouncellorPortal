package com.at.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.at.dto.DashboardResponse;
import com.at.dto.ViewEnquiryFilter;
import com.at.entity.Enquiry;
import com.at.service.CouncellorService;
import com.at.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	EnquiryService eser;
	
	@Autowired
	CouncellorService cser;
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model)
	{
	Enquiry enqObj=new Enquiry();
	model.addAttribute("enq", enqObj);
	return "enquiryForm";
	}
	
	@PostMapping("/addEnq")
	public String handleAddEnquiry(@ModelAttribute("enq") Enquiry enq, HttpServletRequest req, Model model) throws Exception
	{
		 HttpSession session=req.getSession(false);
		 Integer cId=(Integer)session.getAttribute("councellorId");
		
		 boolean isSaved=eser.addEnquiry(enq, cId);
		 if(isSaved)
		 {
			 model.addAttribute("smsg", "Enquiry added");
		 }else
		 {
			 model.addAttribute("emsg", "Failed to add enquiry");
		 }
		 
		 return "enquiryForm";
	}
	
	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model)
	{
		HttpSession session= req.getSession(false);
		Integer councellorId=(Integer) session.getAttribute("councellorId");
			
		DashboardResponse dres=cser.getDashboardInfo(councellorId);
		model.addAttribute("dashboardInfo", dres);
		
		return "dashboard";
	}
	
	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest req, Model model)
	{
		HttpSession session =req.getSession(false);
		Integer cId=(Integer)session.getAttribute("councellorId");
		
		List<Enquiry> EnqList=eser.getAllEnquiries(cId);
		model.addAttribute("Enquiries",EnqList);
		
		ViewEnquiryFilter filt=new ViewEnquiryFilter();
		model.addAttribute("viewEnquiryFilter",filt);
		
		return "enquiry";
	}
	
	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId")Integer enqId, Model model)
	{
		Enquiry enquiry=eser.getEnquiryById(enqId);
		model.addAttribute("enq",enquiry);
		
		return "enquiryForm";
	}
	
	@PostMapping("/filter-enqs")
	public String filterEnquiries(@ModelAttribute("viewEnquiryFilter")ViewEnquiryFilter viewEnquiryFilter ,HttpServletRequest req, Model model)
	{
		HttpSession session = req.getSession();
		Integer cId=(Integer) session.getAttribute("councellorId");
		
		
		List<Enquiry> enqList=eser.getEnquiriesWithfilter(viewEnquiryFilter, cId);
		model.addAttribute("Enquiries", enqList);
		// model.addAttribute("viewEnquiryFilter", viewEnquiryFilter);
		return "enquiry";
	}
	
	
}
