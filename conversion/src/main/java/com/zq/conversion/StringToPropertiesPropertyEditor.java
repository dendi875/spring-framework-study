package com.zq.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * 模拟 String to Properties 的 {@link PropertyEditor} 实现
 *
 * 1. 实现 setAsText(String) 方法
 * 2. 将 String 类型转换成 Properties 类型
 * 3. 临时存储 Properties 对象
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 22:42:45
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements 	PropertyEditor {

	@Override
	public void setAsText(String text) throws java.lang.IllegalArgumentException {
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(text));
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}

		setValue(properties);
	}

	@Override
	public String getAsText() {
		Properties properties = (Properties) getValue();

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
