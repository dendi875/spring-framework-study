package com.zq.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 视频：228丨课外资料：Spring4.1测试配置属性源-@TestPropertySource.mp4
 * PPT：第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 16:51:08
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class) // Spring 注解驱动测试注解
@TestPropertySource(
		properties = "user.name = 张权",  // Inlined Test Properties
		locations = "classpath:/META-INF/test.properties" // class path resource
)
public class TestPropertySourceTest {

	@Value("${user.name}")
	private String userName;

	@Autowired
	private ConfigurableEnvironment environment;

	@Test
	public void testUserName() {
		Assert.assertEquals("张权", userName);


		for (PropertySource ps : environment.getPropertySources()) {
			System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
		}
	}
}
