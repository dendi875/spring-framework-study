package com.zq.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 基于编程条件实现的--条件注解
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 10:16:19
 */
public class EvenProfileCondition implements Condition {

	// 如果 matches 是匹配的，则激活该条件，或者该条件是通过的
	// AnnotatedTypeMetadata 它主要是保留一些标注的元信息
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		return environment.acceptsProfiles("even");
	}
}
