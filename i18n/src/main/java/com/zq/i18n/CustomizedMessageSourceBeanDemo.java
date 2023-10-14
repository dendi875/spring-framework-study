package com.zq.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 *
 * 视频：141丨课外资料：SpringBoot为什么要新建MessageSource Bean？.mp4
 * PPT: 第十二章 Spring 国际化（i18n）.pdf
 *
 * Spring Boot 场景下自定义 {@link MessageSource} Bean
 *
 * @see MessageSource
 * @see {@link AbstractApplicationContext#initMessageSource()}
 * @see MessageSourceAutoConfiguration
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-14 19:53:35
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo { // @Configuration Class


	/**
	 * 在 Spring Boot 场景中，Primary Configuration Sources(Classes) 高于 *AutoConfiguration
	 */
	@Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
	public MessageSource messageSource() {
		return new ReloadableResourceBundleMessageSource();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext =
				// Primary Configuration Class
				new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
						.web(WebApplicationType.NONE)
						.run(args);

		ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

		if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
			// 查找 MessageSource 的 BeanDefinition
			System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
			// 查找 MessageSource Bean
			MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
			System.out.println(messageSource);
		}

		// 关闭应用上下文
		applicationContext.close();
	}
}
