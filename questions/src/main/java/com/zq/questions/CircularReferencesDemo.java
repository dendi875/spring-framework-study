package com.zq.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 视频：加餐4丨BeanFactory如何处理循环依赖的？.mp4
 *
 *  BeanFactory 是如何处理循环依赖的？
 *  1. 预备知识：
 *  	1.1 循环依赖开关（方法）：AbstractAutowireCapableBeanFactory#setAllowCircularReferences
 *  	1.2 单例工程（属性）： DefaultSingletonBeanRegistry#singletonFactories
 *      1.3 获取早期未处理(未初始化) Bean 方法：	AbstractAutowireCapableBeanFactory#getEarlyBeanReference
 *      1.4 早期未处理 Bean (属性)：DefaultSingletonBeanRegistry#earlySingletonObjects
 *
 * 有三个 Map 来处理循环依赖：
 * singletonObjects
 * singletonFactories
 * earlySingletonObjects
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-21 17:00:00
 */
public class CircularReferencesDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class
		context.register(CircularReferencesDemo.class);

		// 这个开关控制循环依赖支不支持，(Spring 默认是支持循环依赖的)
		context.setAllowCircularReferences(true);

		// 刷新 Spring 应用上下文
		context.refresh();

		// 依赖查找 Student 和 ClassRoom
		// Student 对象在创建的依赖 ClassRoom 对象，ClassRoom 对象在创建的时候又依赖于 Student 对象，
		// 所以它是相互依赖(循环依赖)
		System.out.println("student = " +  context.getBean(Student.class));
		System.out.println("classRoom = " + context.getBean(ClassRoom.class));

		// 关闭 Spring 应用下下文
		context.close();

	}

	@Bean
	public static Student student() {
		Student student = new Student();
		student.setId(1L);
		student.setName("张三");
		return student;
	}

	@Bean
	public static ClassRoom classRoom() {
		ClassRoom classRoom = new ClassRoom();
		classRoom.setName("2101");
		return classRoom;
	}
}
