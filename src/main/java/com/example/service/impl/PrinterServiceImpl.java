package com.example.service.impl;

import com.example.annotation.Bean;
import com.example.service.PrinterService;

@Bean
public class PrinterServiceImpl implements PrinterService {
	@Override
	public void printHello() {
		System.out.println("Hello!");
	}
}
