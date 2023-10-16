package com.zq.conversion;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * 在 Spring 中扩展 {@link Converter} 接口的实现示例
 *
 * 把 Properties 类型转为 String 类型，以 {@link User} 中的 contextAsText 为例
 *
 * 视频：170丨扩展Spring类型转换器：为什么最终注册的都是ConditionalGenericConverter？.mp4
 * PPT: 第十五章 Spring 类型转换（Type Conversion）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-16 11:44:20
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return Properties.class.equals(sourceType.getObjectType()) &&
				String.class.equals(targetType.getObjectType());
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Properties properties = (Properties) source;

		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			stringBuilder.append(entry.getKey())
					.append("=")
					.append(entry.getValue())
					.append(System.getProperty("line.separator"));
		}

		return stringBuilder.toString();
	}
}
