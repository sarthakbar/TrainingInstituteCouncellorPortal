package com.at.service;

import com.at.dto.DashboardResponse;
import com.at.entity.Councellor;

public interface CouncellorService {

	public Councellor login(String email, String password);
	public boolean register(Councellor councellor);
	public DashboardResponse getDashboardInfo(Integer councellorId);
}
