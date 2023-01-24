package com.example.autowiring.service.nosuch.impl;

import com.example.annotation.Autowired;
import com.example.annotation.Bean;
import com.example.autowiring.service.nosuch.AccountService;

@Bean
public class AccountServiceImpl implements AccountService {
	@Autowired
	private String string;

	@Override
	public void getUser(int id) {
		System.out.println("getting user");
	}
}
