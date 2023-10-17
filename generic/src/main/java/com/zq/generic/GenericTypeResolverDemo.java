package com.zq.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.resolveReturnType;
import static org.springframework.core.GenericTypeResolver.resolveReturnTypeArgument;

/**
 *
 * 视频：176丨Spring泛型类型辅助类：GenericTypeResolver.mp4
 * PPT: 第十六章 Spring 泛型处理（Generic Resolution）.pdf
 *
 * Spring 泛型类型辅助类
 * • 核心 API - org.springframework.core.GenericTypeResolver
 * • 版本支持：[2.5.2 , )
 * • 处理类型相关（Type）相关方法
 * 		• resolveReturnType
 * 		• resolveType
 * • 处理泛型参数类型（ParameterizedType）相关方法
 * 		• resolveReturnTypeArgument
 * 		• resolveTypeArgument
 * 		• resolveTypeArguments
 * • 处理泛型类型变量（TypeVariable）相关方法
 * 		• getTypeVariableMap
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-17 17:28:57
 */
public class GenericTypeResolverDemo {
	public static void main(String[] args) throws NoSuchMethodException {

		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class,"getString");

		// String 是 Comparable<String> 的具体化，所以 resolveReturnTypeArgument 返回不为 null
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class,"getString");

		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

		// StringList 是 List 泛型参数类型的具体化，所以 resolveReturnTypeArgument 返回不为 null
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

		// ArrayList<Object> 是 List 泛型参数类型的具体化，所以 resolveReturnTypeArgument 返回不为 null
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getArrayListObject");

		// 一旦有了泛型参数类型具体化之后（resolveReturnTypeArgument 返回有值）， TypeVariable 就是常量了
		Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
		System.out.println(typeVariableMap);
	}

	public static String getString() { // 常规类型不具备泛型参数类型 List<E>，所以 resolveReturnTypeArgument 返回 null
		return null;
	}

	public static <E> List<E> getList() { // 泛型参数类型没有具体化，字节码中没有记录，所以 resolveReturnTypeArgument 返回 null
		return null;
	}

	public static StringList getStringList() { // 泛型参数类型有具体化，字节码中有记录，所以 resolveReturnTypeArgument 返回不为 null
		return null;
	}

	public static ArrayList<Object> getArrayListObject() { // 泛型参数类型有具体化，字节码中有记录，所以 resolveReturnTypeArgument 返回不为 null
		return null;
	}

	public static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class<?>... argumentTypes)
			throws NoSuchMethodException {
		Method method = containingClass.getMethod(methodName, argumentTypes);

		Class<?> returnType = resolveReturnType(method, containingClass);
		// 常规类作为方法返回值
		System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

		// ParameterizedType 是需要有一个具体类型
		Class<?> returnTypeArgument = resolveReturnTypeArgument(method, genericIfc);

		System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnTypeArgument);
	}

}
