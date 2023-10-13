package com.zq.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * Spring 资源加载器
 *
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * 视频：128丨Spring资源加载器：为什么说Spring应用上下文也是一种Spring资源加载器？.mp4
 * PPT：第十一章 Spring 资源管理（Resources）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 11:57:45
 */
public class EncodeFileSystemResourceLoaderDemo {
	public static void main(String[] args) throws IOException {
		String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/com/zq/resource/EncodeFileSystemResourceDemo.java";
		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
		Resource resource = fileSystemResourceLoader.getResource(currentJavaFilePath);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}
	}
}
