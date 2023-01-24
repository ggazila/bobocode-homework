package com.example.autowiring.service.normal.impl;

import com.example.annotation.Autowired;
import com.example.annotation.Bean;
import com.example.autowiring.service.normal.HelperService;
import com.example.autowiring.service.normal.RoleService;

@Bean
public class RoleServiceImpl implements RoleService {

	@Autowired
	private HelperService helperService;

	@Override
	public void printRandomRole() {
		System.out.println("ADMIN");
	}
}
