package com.zq.aop.overview;

/**
 * {@link ClassLoader} 示例
 * <p>
 * 视频：03丨知识储备：基础、基础，还是基础！.mp4
 * PPT: 第一章 - Spring AOP 总览.pdf
 * <p>
 * • Java 基础
 * • Java ClassLoading 类加载的机制
 * 类加载可以分为两个部分，一是类的搜索，另一个是类的加载
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @see ClassLoader#loadClass(String)
 * @see java.net.URLClassLoader
 * @since 2023-10-21 22:26:38
 */
public class ClassLoadingDemo {

	public static void main(String[] args) {
		// 通过当前线程加载它的 ClassLoader
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(classLoader);

		ClassLoader parentClassLoader = classLoader;

		while (true) {
			parentClassLoader = parentClassLoader.getParent();
			if (parentClassLoader == null) {
				break;
			}
			System.out.println(parentClassLoader);
		}
	}
}
