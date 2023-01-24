package com.example.context.impl;

import com.example.autowiring.service.normal.RoleService;
import com.example.exception.NoSuchBeanException;
import com.example.exception.NoUniqueBeanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AutowiredTest {

	private ApplicationContextImpl context;


	void createContext(String packageName) {
		context = new ApplicationContextImpl(packageName);
	}

	@Test
	void testGetBeanShouldReturnBean() {
		createContext("com.example.autowiring.service.normal");
		var result = context.getBean(RoleService.class);

		assertNotNull(result);
	}

	@Test
	void testGetBeanShouldThrowNoSuchBeanException() {
		assertThrows(NoSuchBeanException.class,
				() -> createContext("com.example.autowiring.service.nosuch"));
	}

	@Test
	void testGetBeanShouldThrowNoUniqueBeanException() {
		assertThrows(NoUniqueBeanException.class,
				() -> createContext("com.example.autowiring.service.nouniq"));
	}

}
