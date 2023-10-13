package com.zq.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-13 14:41:54
 */
public interface ResourceUtils {

	static String getContent(Resource resource) {
		try {
			return getContent(resource, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	static String getContent(Resource resource, String encoding) throws IOException {
		EncodedResource encodedResource = new EncodedResource(resource, encoding);
		try (Reader reader = encodedResource.getReader()) {
			return IOUtils.toString(reader);
		}
	}
}
