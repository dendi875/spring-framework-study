package com.zq.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Java 文本格式化 {@link MessageFormat} 示例
 * • 核心接口
 * 		• java.text.MessageFormat
 * • 基本用法
 * 		• 设置消息格式模式- new MessageFormat(...)
 * 		• 格式化 - format(new Object[]{...})
 * • 消息格式模式
 * 		• 格式元素：{ArgumentIndex (,FormatType,(FormatStyle))}
 * 		• FormatType：消息格式类型，可选项，每种类型在 number、date、time 和 choice 类型选其一
 * 		• FormatStyle：消息格式风格，可选项，包括：short、medium、long、full、integer、currency、percent
 *
 * Java 文本格式化
 * • 高级特性
 * 		• 重置消息格式模式
 * 		• 重置 java.util.Locale
 * 		• 重置 java.text.Format
 *
 *
 * 视频：138丨Java文本格式化：MessageFormat脱离Spring场景，能力更强大？.mp4
 * PPT:第十二章 Spring 国际化（i18n）.pdf
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-14 18:32:31
 */
public class MessageFormatDemo {
	public static void main(String[] args) {
		int planet = 7;
		String event = "a disturbance in the Force";

		String messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
		MessageFormat messageFormat = new MessageFormat(messageFormatPattern);
		String result = messageFormat.format(new Object[]{planet, new Date(), event});
		System.out.println(result);

		// 重置 MessageFormatPattern
		// applyPattern
		messageFormatPattern = "This is a text : {0}, {1}, {2}";
		messageFormat.applyPattern(messageFormatPattern);
		result = messageFormat.format(new Object[]{"Hello,World", "666"});
		System.out.println(result);

		// 重置 Locale
		messageFormat.setLocale(Locale.ENGLISH);
		messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
		messageFormat.applyPattern(messageFormatPattern);
		result = messageFormat.format(new Object[]{planet, new Date(), event});
		System.out.println(result);

		// 重置 Format
		// 根据参数索引来设置 Pattern
		messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
		result = messageFormat.format(new Object[]{planet, new Date(), event});
		System.out.println(result);
	}
}
