package com.zq.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring 与 Bean Validation 的整合
 *
 * 视频：148丨Validator的救赎：如果没有BeanValidation，Validator将会在哪里吗？.mp4
 * PPT:第十三章 Spring 校验（Validation）.pdf
 *
 * Bean Validation 与 Validator 适配
 * 	• 核心组件 - org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
 * 	• 依赖 Bean Validation - JSR-303 or JSR-349 provider
 * 	• Bean 方法参数校验 - org.springframework.validation.beanvalidation.MethodValidationPostProcessor
 *
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 12:12:16
 */
public class SpringBeanValidationDemo {
	public static void main(String[] args) {
		// 我们以 XML 方式为例
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

		Validator validator = applicationContext.getBean(Validator.class);
		System.out.println(validator instanceof LocalValidatorFactoryBean);

		PersonProcessor personProcessor = applicationContext.getBean(PersonProcessor.class);
		personProcessor.process(new Person());

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}

	// XML 与 Annotation 的混合
	@Component
	@Validated
	static class PersonProcessor {

		public void process(@Valid Person person) {
			System.out.println(person);
		}
	}

	static class Person {

		@NotNull
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
