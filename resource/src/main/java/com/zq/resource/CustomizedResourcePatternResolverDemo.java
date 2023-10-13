package com.zq.resource;

import com.zq.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * 自定义 {@link ResourcePatternResolver} 实现
 *
 * Spring 通配路径资源加载器
 * • 通配路径 ResourceLoader
 * 		• org.springframework.core.io.support.ResourcePatternResolver
 * 		• org.springframework.core.io.support.PathMatchingResourcePatternResolver
 * • 路径匹配器
 * 		• org.springframework.util.PathMatcher
 * 		• Ant 模式匹配实现 - org.springframework.util.AntPathMatcher
 *
 * Spring 通配路径资源扩展
 * • 实现 org.springframework.util.PathMatcher
 * • 重置 PathMatcher
 * 		• PathMatchingResourcePatternResolver#setPathMatch
 *
 * 视频：129丨Spring通配路径资源加载器：如何理解路径通配Ant模式？.mp4
 * 		130丨Spring通配路径模式扩展：如何扩展路径匹配的规则？.mp4
 * PPT：第十一章 Spring 资源管理（Resources）.pdf
 *
 *
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 14:16:46
 */
public class CustomizedResourcePatternResolverDemo {
	public static void main(String[] args) throws IOException {

		String currentJavaPackagePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/com/zq/resource/*.java";


		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(fileSystemResourceLoader);
		Resource[] resources = pathMatchingResourcePatternResolver.getResources(currentJavaPackagePath);

		Arrays.stream(resources).map(ResourceUtils::getContent).forEach(System.out::println);
	}

	static class JavaFilePathMatcher implements PathMatcher {

		@Override
		public boolean isPattern(String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean match(String pattern, String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean matchStart(String pattern, String path) {
			return false;
		}

		@Override
		public String extractPathWithinPattern(String pattern, String path) {
			return null;
		}

		@Override
		public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
			return null;
		}

		@Override
		public Comparator<String> getPatternComparator(String path) {
			return null;
		}

		@Override
		public String combine(String pattern1, String pattern2) {
			return null;
		}
	}
}
