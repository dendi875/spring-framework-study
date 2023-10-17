package com.zq.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 泛型擦写示例
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-17 16:56:23
 */
public class GenericEraseDemo {

	public static void main(String[] args) {
		Collection<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		// list.add(1); // 编译错误

		// 泛型擦写
		Collection tmp = list;
		tmp.add(1); // 编译通过

		System.out.println(tmp);
	}
}
