package com.zq.bean.scope.web;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 22:08:39
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

	@Bean
	 //@RequestScope
	@SessionScope
	//@ApplicationScope
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("张权");
		return user;
	}

}
