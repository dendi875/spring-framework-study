package com.zq.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频：177丨Spring泛型集合类型辅助类：GenericCollectionTypeResolver.mp4
 * PPT: 第十六章 Spring 泛型处理（Generic Resolution）.pdf
 *
 * Spring 泛型集合类型辅助类
 * • 核心 API - org.springframework.core.GenericCollectionTypeResolver
 * • 版本支持：[2.0 , 4.3]
 * • 替换实现：org.springframework.core.ResolvableType
 * • 处理 Collection 相关
 * 		• getCollection*Type
 * • 处理 Map 相关
 * 		• getMapKey*Type
 * 		• getMapValue*Type
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-17 18:20:36
 */
public class GenericCollectionTypeResolverDemo {

	private static StringList stringList;

	private static List<String> strings;

	public static void main(String[] args) throws NoSuchFieldException {
		// getCollectionType 返回的是「具体化的泛型参数类型的集合成员的类型」
		System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
		System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

		Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
		System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

		field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
		System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
	}
}
