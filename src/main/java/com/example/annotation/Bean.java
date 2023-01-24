package com.example.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a bean. An instance of a class annotated with this build will be created by the Application context
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

	/**
	 * A name of the bean. If no name is provided then the name of the class used (in camelCase)
	 *
	 * @return a name of the bean or an empty string as a default value
	 */
	String value() default StringUtils.EMPTY;
}
