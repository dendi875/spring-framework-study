package com.zq.conversion;

import java.beans.PropertyEditor;

/**
 * 视频：161丨基于JavaBeans接口的类型转换：Spring是如何扩展PropertyEditor接口实现类型转换的？.mp4
 * PPT: 第十五章 Spring 类型转换（Type Conversion）.pdf
 *
 * 基于 JavaBeans 接口的类型转换
 * • 核心职责
 * 		• 将 String 类型的内容转化为目标类型的对象
 * • 扩展原理
 * 		• Spring 框架将文本内容传递到 PropertyEditor 实现的 setAsText(String) 方法
 * 		• PropertyEditor#setAsText(String) 方法实现将 String 类型转化为目标类型的对象
 * 		• 将目标类型的对象传入 PropertyEditor#setValue(Object) 方法
 * 		• PropertyEditor#setValue(Object) 方法实现需要临时存储传入对象
 * 		• Spring 框架将通过 PropertyEditor#getValue() 获取类型转换后的对象
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 22:41:00
 */
public class PropertyEditorDemo {
	public static void main(String[] args) {
		// 模拟 Spring Framework 操作
		// 假设有一段文本
		String name = "name = 张权";
		PropertyEditor propertyEditor =  new StringToPropertiesPropertyEditor();
		propertyEditor.setAsText(name);

		System.out.println(propertyEditor.getValue());
		System.out.println(propertyEditor.getAsText());
	}
}
