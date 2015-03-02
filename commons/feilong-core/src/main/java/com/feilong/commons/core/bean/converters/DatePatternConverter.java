/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.commons.core.bean.converters;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.text.DateFormatUtil;
import com.feilong.commons.core.util.Validator;

/**
 * The Class DateConverter,依赖apache commons-beanutils工具包<br>
 * 
 * for example:
 * 
 * <pre>
 * User a = new User();
 * a.setId(5L);
 * Date now = new Date();
 * a.setDate(now);
 * User b = new User();
 * 
 * DatePatternConverter converter = new DatePatternConverter(DatePattern.forToString, Locale.US);
 * ConvertUtils.register(converter, Date.class);
 * 
 * BeanUtil.copyProperty(b, a, &quot;date&quot;);
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-4 0:35:31
 * @see org.apache.commons.beanutils.converters.DateConverter
 * @see org.apache.commons.beanutils.converters.DateTimeConverter
 * @see org.apache.commons.beanutils.converters.AbstractConverter
 * @see org.apache.commons.beanutils.Converter
 * @see org.apache.commons.beanutils.ConvertUtils#register(org.apache.commons.beanutils.Converter, Class)
 * @since 1.0.0
 */
public class DatePatternConverter extends DateTimeConverter{

	/** pattern {@link DatePattern}. */
	private String	pattern;

	/** The locale. */
	private Locale	locale	= Locale.getDefault();

	/**
	 * Instantiates a new date converter.
	 * 
	 * @param datePattern
	 *            {@link DatePattern}
	 * 
	 * @see org.apache.commons.beanutils.converters.DateConverter
	 * @see org.apache.commons.beanutils.converters.DateTimeConverter
	 * @see org.apache.commons.beanutils.converters.AbstractConverter
	 * @see org.apache.commons.beanutils.Converter
	 * @see org.apache.commons.beanutils.ConvertUtils#register(org.apache.commons.beanutils.Converter, Class)
	 */
	public DatePatternConverter(String datePattern){
		this.pattern = datePattern;
	}

	/**
	 * Instantiates a new date converter.
	 * 
	 * @param datePattern
	 *            {@link DatePattern}
	 * @param locale
	 *            the locale
	 * 
	 * @see org.apache.commons.beanutils.converters.DateConverter
	 * @see org.apache.commons.beanutils.converters.DateTimeConverter
	 * @see org.apache.commons.beanutils.converters.AbstractConverter
	 * @see org.apache.commons.beanutils.Converter
	 * @see org.apache.commons.beanutils.ConvertUtils#register(org.apache.commons.beanutils.Converter, Class)
	 */
	public DatePatternConverter(String datePattern, Locale locale){
		this.pattern = datePattern;
		this.locale = locale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.converters.AbstractConverter#getDefaultType()
	 */
	@Override
	protected Class<?> getDefaultType(){
		return Date.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.converters.AbstractConverter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type,Object value){
		if (Validator.isNullOrEmpty(pattern)){
			throw new IllegalArgumentException("value can't be null/empty!");
		}

		if (Validator.isNullOrEmpty(value)){
			return null;
		}
		Date dateObj = null;
		if (value instanceof String){
			dateObj = DateFormatUtil.parse(value.toString(), pattern, locale);
		}
		return (T) dateObj;
	}

	/**
	 * Sets the pattern {@link DatePattern}.
	 * 
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}
}