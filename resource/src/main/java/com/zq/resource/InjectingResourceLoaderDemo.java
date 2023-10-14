package com.zq.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 *
 * 注入 {@link ResourceLoader} 对象示例
 *
 *依赖注入 ResourceLoader
 * • 方法一：实现 ResourceLoaderAware 回调
 * • 方法二：@Autowired 注入 ResourceLoader
 * • 方法三：注入 ApplicationContext 作为 ResourceLoader
 *
 *
 * 视频：132丨依赖注入ResourceLoader：除了ResourceLoaderAware回调注入，还有哪些注入方法？.mp4
 * PPT：第十一章 Spring 资源管理（Resources）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 15:04:24
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {


	private ResourceLoader awareResourceLoader; // 方法一

	@Autowired
	private ResourceLoader autoWiredResourceLoader; // 方法二

	@Autowired
	private ApplicationContext applicationContext; // 方法三

	/**
	 * 得到结论 awareResourceLoader、autoWiredResourceLoader、applicationContext 这三个对象都是 AbstractApplicationContext
	 * 这个类的实例化对象。
	 *
	 * 方法一：实现 ResourceLoaderAware 回调
	 * 		在 AbstractApplicationContext#prepareBeanFactory 方法中被配置，具体代码：
	 * 			beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
	 * 方法二：@Autowired 注入 ResourceLoader
	 * 		在 AbstractApplicationContext#prepareBeanFactory 方法中被配置，具体代码：
	 * 			beanFactory.registerResolvableDependency(ResourceLoader.class, this);
	 *方法三：注入 ApplicationContext 作为 ResourceLoader
	 * 		在 AbstractApplicationContext#prepareBeanFactory 方法中被配置，具体代码：
	 * 			beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
	 */
	@PostConstruct
	public void init() {
		System.out.println("awareResourceLoader == autoWiredResourceLoader : " + (awareResourceLoader == autoWiredResourceLoader));
		System.out.println("awareResourceLoader == applicationContext : " + (awareResourceLoader == applicationContext));

	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 将当前类作为 Configuration Class
		context.register(InjectingResourceLoaderDemo.class);
		// 启动 Spring 应用上下文
		context.refresh();
		// 关闭 Spring 应用上下文
		context.close();
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.awareResourceLoader = resourceLoader;
	}
}
