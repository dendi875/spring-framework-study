package com.zq.resource;

import com.zq.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 *
 * 注入 {@link Resource} 对象示例
 *
 * {@link Value} 除了注入外部化配置化，还能依赖注入 Spring Resource，包括单个Resource和集合Resource
 *
 *
 * 视频：131丨依赖注入Spring Resource：如何在XML和Java注解场景注入Resource对象？.mp4
 * PPT：第十一章 Spring 资源管理（Resources）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 15:04:24
 */
public class InjectingResourceDemo {

	// 通过 @Value 注入单个 Spring Resource
	@Value("classpath:/META-INF/default.properties")
	private Resource defaultPropertiesResource;

	// 通过 @Value 注入多个 Spring Resource
	@Value("classpath*:/META-INF/*.properties")
	private Resource[] productionPropertiesResource;

	// @Value 注解在 @PostConstruct 注解之前执行
	@PostConstruct
	public void init() {
		System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
		System.out.println("=============");
		Stream.of(productionPropertiesResource).map(ResourceUtils::getContent).forEach(System.out::println);
		System.out.println("=============");
		System.out.println(System.getProperty("user.dir"));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 将当前类作为 Configuration Class
		context.register(InjectingResourceDemo.class);
		// 启动 Spring 应用上下文
		context.refresh();
		// 关闭 Spring 应用上下文
		context.close();
	}
}
