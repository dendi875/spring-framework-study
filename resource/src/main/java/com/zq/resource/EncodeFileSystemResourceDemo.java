package com.zq.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * Spring Resource 接口扩展
 *
 * {@link org.springframework.core.io.WritableResource } 的扩展实现，以及 FileSystemResource、EncodedResource 的使用
 *
 * 视频：127丨SpringResource接口扩展：Resource能否支持写入以及字符集编码？.mp4
 * PPT：第十一章 Spring 资源管理（Resources）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 11:57:45
 */
public class EncodeFileSystemResourceDemo {
	public static void main(String[] args) throws IOException {
		String currentJavaFilePath = System.getProperty("user.dir") + "/resource/src/main/java/com/zq/resource/EncodeFileSystemResourceDemo.java";
		File file = new File(currentJavaFilePath);
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}
	}
}
