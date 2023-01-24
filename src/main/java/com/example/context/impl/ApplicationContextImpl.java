package com.example.context.impl;


import com.example.annotation.Bean;
import com.example.context.ApplicationContext;
import com.example.exception.NoSuchBeanException;
import com.example.exception.NoUniqueBeanException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class ApplicationContextImpl implements ApplicationContext {
	private final Map<String, Object> beanMap = new HashMap<>();

	public ApplicationContextImpl(String packageName) {
		scanPackage(packageName);
	}

	@SneakyThrows
	private void scanPackage(String packageName) {
		Reflections reflections = new Reflections(packageName, Scanners.TypesAnnotated);
		Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Bean.class);
		for (Class<?> bean : typesAnnotatedWith) {
			String beanCustomName = bean.getAnnotation(Bean.class).value();
			String beanName = StringUtils.defaultIfEmpty(beanCustomName, WordUtils.uncapitalize(bean.getSimpleName()));
			beanMap.put(beanName, bean.getDeclaredConstructor().newInstance());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> beanType) {
		List<T> beans = (List<T>) beanMap.values().stream()
				.filter(o -> beanType.isAssignableFrom(o.getClass()))
				.toList();

		if (beans.isEmpty()) {
			throw new NoSuchBeanException("No beans found for " + beanType.getName());
		} else if (beans.size() > 1) {
			throw new NoUniqueBeanException("There are %s beans found for %s, expected 1.".formatted(beans.size(), beanType.getName()));
		}

		return beans.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name, Class<T> beanType) {
		T bean = (T) beanMap.get(name);
		if (bean == null) {
			throw new NoSuchBeanException("No beans found for " + beanType.getName());
		}
		return bean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getAllBeans(Class<T> beanType) {
		return (Map<String, T>) beanMap.entrySet().stream()
				.filter(entry -> beanType.isAssignableFrom(entry.getValue().getClass()))
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
