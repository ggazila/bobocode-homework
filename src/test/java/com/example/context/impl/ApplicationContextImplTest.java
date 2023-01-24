package com.example.context.impl;

import com.example.exception.NoSuchBeanException;
import com.example.exception.NoUniqueBeanException;
import com.example.service.DbService;
import com.example.service.PrinterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextImplTest {

	private ApplicationContextImpl applicationContextImplUnderTest;

	@BeforeEach
	void setUp() {
		applicationContextImplUnderTest = new ApplicationContextImpl("com.example.service");
	}

	@Test
	void testGetBeanShouldReturnBean() {
		var result = applicationContextImplUnderTest.getBean(PrinterService.class);

		assertNotNull(result);
	}

	@Test
	void testGetBeanByNameShouldReturnBean() {
		var result = applicationContextImplUnderTest.getBean("db", DbService.class);
		assertNotNull(result);
	}

	@Test
	void testGetAllBeansShouldReturnBean() {
		var result = applicationContextImplUnderTest.getAllBeans(DbService.class);
		assertEquals(2, result.size());
	}

	@Test
	void testGetBeanShouldThrowNoSuchBeanException() {
		assertThrows(NoSuchBeanException.class, () -> applicationContextImplUnderTest.getBean(String.class));
	}

	@Test
	void testGetBeanByNameShouldThrowNoSuchBeanException() {
		assertThrows(NoSuchBeanException.class, () -> applicationContextImplUnderTest.getBean("str", String.class));
	}

	@Test
	void testGetBeanShouldThrowNoUniqueBeanException() {
		assertThrows(NoUniqueBeanException.class, () -> applicationContextImplUnderTest.getBean(DbService.class));
	}
}
