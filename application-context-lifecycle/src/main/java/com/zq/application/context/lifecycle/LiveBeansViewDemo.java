package com.zq.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

/**
 * {@link LiveBeansView} 示例
 *
 * 视频：240丨Spring应用上下刷新完成阶段.mp4
 * PPT: 第二十章 Spring 应用上下文生命周期（ApplicationContext Lifecycle）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 20:50:00
 */
public class LiveBeansViewDemo {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 添加 LiveBeansView 的 ObjectName 的 domain
		System.setProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME, "com.zq");

		// 注册 Configuration Class
		context.register(LiveBeansViewDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		System.out.println("按任意键继续...");
		System.in.read();

		// 关闭 Spring 应用上下文
		context.close();
	}
}
