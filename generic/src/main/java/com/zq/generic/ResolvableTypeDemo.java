package com.zq.generic;

import org.springframework.core.ResolvableType;

/**
 *
 * 视频：179丨Spring4.2泛型优化实现-ResolvableType.mp4
 * PPT:第十六章 Spring 泛型处理（Generic Resolution）.pdf
 *
 * Spring 4.0 泛型优化实现 - ResolvableType
 * • 核心 API - org.springframework.core.ResolvableType
 * • 起始版本：[4.0 , )
 * • 扮演角色：GenericTypeResolver 和 GenericCollectionTypeResolver 替代者
 * • 工厂方法：for* 方法
 * • 转换方法：as* 方法
 * • 处理方法：resolve* 方法
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-17 18:41:52
 */
public class ResolvableTypeDemo {
	public static void main(String[] args) {

		// StringList <- ArrayList <- AbstractList <- List <- Collection
		ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

		System.out.println(resolvableType.getSuperType());
		System.out.println(resolvableType.getSuperType().getSuperType());

		// 获取原始类型
		System.out.println(resolvableType.asCollection().resolve()); // interface java.util.Collection
		// 获取泛型参数类型
		System.out.println(resolvableType.asCollection().resolveGeneric(0)); // class java.lang.String
	}
}
