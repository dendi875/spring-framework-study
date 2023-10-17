package com.zq.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * 视频： 175丨Java5类型接口-Type：Java类型到底是Type还是Class？.mp4
 *
 * PPT: 第十六章 Spring 泛型处理（Generic Resolution）.pdf
 * <p>
 * Java 5 类型接口
 * * Java 泛型反射 API
 * * 泛型信息（Generics Info） 			java.lang.Class#getGenericInfo()
 * * 泛型参数（Parameters）    			java.lang.reflect.ParameterizedTyp
 * * 泛型父类（Super Classes） 			java.lang.Class#getGenericSuperclass()
 * * 泛型接口（Interfaces）    			java.lang.Class#getGenericInterfaces()
 * * 泛型声明（Generics Declaration） 	java.lang.reflect.GenericDeclaration
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-17 17:08:01
 */
public class GenericAPIDemo {
	public static void main(String[] args) {
		// 原生类型 int long float
		Class intClass = int.class;

		// 数组类型 int[] object[]
		Class objectArrayClass = Object[].class;

		// 原始类型 java.lang.String
		Class rawClass = String.class;

		// 泛型参数类型 AbstractList<E>
		ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

		System.out.println(parameterizedType.toString());
		System.out.println(parameterizedType.getRawType());

		// 泛型类型变量 E
		Type[] typeVariables = parameterizedType.getActualTypeArguments();
		Stream.of(typeVariables)
				.map(TypeVariable.class::cast) // Type 类型转为 TypeVariable 类型
				.forEach(System.out::println);
	}
}
