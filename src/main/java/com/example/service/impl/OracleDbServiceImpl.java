package com.example.service.impl;

import com.example.annotation.Bean;
import com.example.service.DbService;

@Bean
public class OracleDbServiceImpl implements DbService {
	@Override
	public void connect() {
		System.out.println("connect to oracle");
	}
}
