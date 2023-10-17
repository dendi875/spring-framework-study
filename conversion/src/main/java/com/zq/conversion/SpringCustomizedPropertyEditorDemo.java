package com.zq.conversion;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * 自定义 {@link PropertyEditor} 实现
 * <p>
 * 视频： 163丨自定义PropertyEditor扩展：不尝试怎么知道它好不好用？.mp4
 * PPT: 第十五章 Spring 类型转换（Type Conversion）.pdf
 * <p>
 * 自定义 PropertyEditor 扩展
 * • 扩展模式
 * • 扩展 java.beans.PropertyEditorSupport 类
 * • 实现 org.springframework.beans.PropertyEditorRegistrar
 * • 实现 registerCustomEditors(org.springframework.beans.PropertyEditorRegistry) 方法
 * • 将 PropertyEditorRegistrar 实现注册为 Spring Bean
 * • 向 org.springframework.beans.PropertyEditorRegistry 注册自定义 PropertyEditor 实现
 * • 通用类型实现 registerCustomEditor(Class<?>, PropertyEditor)
 * • Java Bean 属性类型实现：registerCustomEditor(Class<?>, String, PropertyEditor)
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @see StringToPropertiesPropertyEditor
 * @see PropertiesToStringConverter
 * @since 2023-10-16 10:48:01
 */
public class SpringCustomizedPropertyEditorDemo {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

		// ApplicationContext 在创建的时候 AbstractApplicationContext 会去查找一个名为 "conversionService" 的 Bean
		// 它把这个 "conversionService" 的 Bean 传递到 BeanFactory 中到 AbstractBeanFactory 中
		// AbstractBeanFactory#getConversionService 把 "conversionService" 的 Bean 传到 BeanDefinition 中
		// Bean 在创建的时候它是一个 BeanDefinition，然后把 BeanDefinition 变成一个 BeanWrapper
		// 在 BeanWrapper 中它涉及到参数的转换，比如说 PropertyValues ，这就是它的数据来源，它转换时会用到 setPropertyValues(PropertyValues) 这个方法
		// setPropertyValues 方法中调用 TypeConverter 的实现，也就是调用 TypeConverter#convertIfNecessary
		// TypeConverter#convertIfNecessary 调用 TypeConverterDelegate#convertIfNecessary
		// TypeConverterDelegate#convertIfNecessary 调用 PropertyEditor 或者 ConversionService

		// AbstractApplicationContext -> "conversionService" ConversionService Bean
		// -> ConfigurableBeanFactory#setConversionService(ConversionService)
		// -> AbstractAutowireCapableBeanFactory.instantiateBean
		// -> AbstractBeanFactory#getConversionService ->
		// BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertyValues）->
		// setPropertyValues(PropertyValues) -> TypeConverter#convertIfNecessnary
		// TypeConverterDelegate#convertIfNecessnary  -> PropertyEditor or ConversionService

		User user = applicationContext.getBean("user", User.class);

		// context 字段是从 String 类型转为 Properties 类型，StringToPropertiesPropertyEditor
		// contextAsText 字段是从 Properties 类型转为 String  类型 ，PropertiesToStringConverter
		System.out.println(user);

		// 显示关闭 Spring 应用上下文
		applicationContext.close();
	}
}
