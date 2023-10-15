package com.zq.validation;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 *
 * {@link Errors} 接口设计和相关方法的使用
 *
 * Errors 文案来源
 * 		• Errors 文案生成步骤
 * 			• 选择 Errors 实现（如：org.springframework.validation.BeanPropertyBindingResult）
 * 			• 调用 reject 或 rejectValue 方法
 * 			• 获取 Errors 对象中 ObjectError 或 FieldError
 * 			• 将 ObjectError 或 FieldError 中的 code 和 args，关联 MessageSource 实现（如：ResourceBundleMessageSource）
 *
 * 视频：145丨Errors接口设计：复杂得没有办法理解？.mp4
 * PPT:第十三章 Spring 校验（Validation）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 11:21:17
 */
public class ErrorsMessageDemo {
	public static void main(String[] args) {
		User user = new User();
		user.setName("zq");

		// 1. 选择 Errors - BeanPropertyBindingResult
		Errors errors = new BeanPropertyBindingResult(user,"user");

		// 2. 调用 reject 或 rejectValue
		// reject 生成 ObjectError
		// reject 生成 FieldError
		errors.reject("user.properties.not.null");
		// user.name 相当于 user.getName()
		errors.rejectValue("name", "name.required");

		// 3. 获取 Errors 中 ObjectError 和 FieldError
		// FieldError is ObjectError
		List<ObjectError> globalErrors = errors.getGlobalErrors();
		List<FieldError> fieldErrors = errors.getFieldErrors();
		List<ObjectError> allErrors = errors.getAllErrors();

		// 4. 通过 ObjectError 和 FieldError 中的 code 和 args 来关联 MessageSource 实现
		MessageSource messageSource = createMessageSource();
		for (ObjectError error : allErrors) {
			String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
			System.out.println(message);
		}
	}

	private static MessageSource createMessageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
		messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null.");
		messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null.");
		return messageSource;
	}
}
