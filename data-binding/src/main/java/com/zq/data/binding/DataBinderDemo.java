package com.zq.data.binding;


import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * {@link DataBinder} 示例
 *
 *
 * DataBinder 绑定特殊场景分析
 * 		• 当 PropertyValues 中包含名称 x 的 PropertyValue，目标对象 B 不存在 x 属性，当 bind 方法执行时，会发生什么？
 * 		• 当 PropertyValues 中包含名称 x 的 PropertyValue，目标对象 B 中存在 x 属性，当 bind 方法执行时，如何避免 B 属性 x 不被绑定？
 * 		• 当 PropertyValues 中包含名称 x.y 的 PropertyValue，目标对象 B 中存在 x 属性（嵌套 y 属性），当 bind 方法执行时，会发生什么？
 *
 * 视频：153丨DataBinder绑定控制参数：ignoreUnknownFields和ignoreInvalidFields有什么作用？.mp4
 * PPT:第十四章 Spring 数据绑定（Data Binding）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 16:02:25
 */
public class DataBinderDemo {
	public static void main(String[] args) {

		User user = new User();

		// 创建 DataBinder 对象
		DataBinder dataBinder = new DataBinder(user, "user");

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "张权");

		// 情况一：PropertyValues 存在 User 中不存在的属性值时，DataBinder.bind 时会发生什么
		// 结论：DataBinder 会忽略未知的属性
		source.put("age", 31);

		// 情况二：PropertyValues 存在一个嵌套属性时，DataBinder.bind 时会发生什么
		// 结论：会支嵌套绑定
		source.put("company.name", "dendi875");

		// 细粒度的控制 DataBinder 的行为
		// 1. ignoreUnknownFields 是否忽略未知字段，默认值：true
		// dataBinder.setIgnoreUnknownFields(false);

		// 2. autoGrowNestedPaths 属性，是否自动增加嵌套路径，默认值：true
		dataBinder.setAutoGrowNestedPaths(false);

		// 3. ignoreInvalidFields 是否忽略非法字段，默认值：false，修改为 true 是不生效的
		dataBinder.setIgnoreInvalidFields(true);

		// 4. requiredFields 必须绑定字段，需要结合 BindingResult 来使用
		dataBinder.setRequiredFields("id", "name", "city");

		// 创建 PropertyValues 并进行绑定
		PropertyValues propertyValues = new MutablePropertyValues(source);
		dataBinder.bind(propertyValues);

		// 输出 user 内容
		System.out.println(user);

		// 获取绑定结果，它里面包含了错误文字 code，它不会抛出异常
		BindingResult bindingResult = dataBinder.getBindingResult();
		System.out.println(bindingResult);
	}
}
