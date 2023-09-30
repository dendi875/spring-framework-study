package com.zq.spring.ioc.overview.container;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Annotation 通过 ApplicationContext 的应用示例
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 10:12:27
 */
@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
		applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
		// 启动应用上下文
		applicationContext.refresh();

		lookupCollectionByType(applicationContext);

		// 停止
		applicationContext.close();
	}

	@Bean
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("小张");
		return user;
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有的 User 集合对象：" + users);
		}
	}
}
