package com.at.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.at.dto.DashboardResponse;
import com.at.entity.Councellor;
import com.at.service.CouncellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class CouncellorController {

	@Autowired
	private CouncellorService cservice;  
	
	public CouncellorController(CouncellorService cservice)
	{
		this.cservice=cservice;
	}
	
	
	@GetMapping("/")
	public String index(Model model)
	{ 
		Councellor cobj=new Councellor();
		
		model.addAttribute("councellor", cobj);
		
		return "index";
	}
	
	
	@PostMapping("/login")
	public String login(Councellor councellor,HttpServletRequest request , Model model)
	{
		Councellor c= cservice.login(councellor.getEmail(), councellor.getPassword());
		
		if(c==null)
		{
			model.addAttribute("emsg", "invalid credentials");
			return "index";
		}else
		{
			HttpSession session= request.getSession(true);
			session.setAttribute("councellorId", c.getCouncellorId());
			
			
			DashboardResponse dobj= cservice.getDashboardInfo(c.getCouncellorId());
			model.addAttribute("dashboardInfo",dobj);
			return "dashboard";
		}
	}
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		Councellor cobj=new Councellor();
		
		model.addAttribute("councellor", cobj);
		
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Councellor councellor, Model model)
	{
		boolean isRegistered= cservice.register(councellor);
		
		
		
		if(isRegistered)
		{
			model.addAttribute("smsg", "Registration success..");
			
		}else
		{
			model.addAttribute("emsg", "Duplicate Email");
		}
		
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req)
	{
		//get existing session and invalidate it
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "redirect:/";
	}

	
} 
