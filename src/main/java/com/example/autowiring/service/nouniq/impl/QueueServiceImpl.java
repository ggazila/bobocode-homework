package com.example.autowiring.service.nouniq.impl;

import com.example.annotation.Autowired;
import com.example.annotation.Bean;
import com.example.autowiring.service.nouniq.QueueService;
import com.example.autowiring.service.nouniq.SpecialService;

@Bean
public class QueueServiceImpl implements QueueService {
	@Autowired
	private SpecialService specialService;

	@Override
	public void queueTask() {
		System.out.println("queue task");
	}
}
