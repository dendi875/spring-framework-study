package com.zq.validation;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.zq.validation.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义 {@link Validator} 的实现
 * <p>
 * 视频：147丨自定义Validator：为什么说Validator容易实现，却难以维护？.mp4
 * PPT: 第十三章 Spring 校验（Validation）.pdf
 * <p>
 * 实现 org.springframework.validation.Validator 接口
 * • 实现 supports 方法
 * • 实现 validate 方法
 * • 通过 Errors 对象收集错误
 * • ObjectError：对象（Bean）错误：
 * • FieldError：对象（Bean）属性（Property）错误
 * • 通过 ObjectError 和 FieldError 关联 MessageSource 实现获取最终文案
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-15 11:51:43
 */
public class ValidationDemo {

	public static void main(String[] args) {
		Validator userValidator = new UserValidator();
		User user = new User();
		System.out.println("user 对象是否被 UserValidator 支持校验：" + userValidator.supports(User.class));
		// 创建 Errors 对象
		Errors errors = new BeanPropertyBindingResult(user, "user");
		userValidator.validate(user, errors);

		MessageSource messageSource = createMessageSource();
		for (ObjectError error : errors.getAllErrors()) {
			String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
			System.out.println(message);
		}
	}

	static class UserValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return User.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			User user = (User) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		}
	}
}
