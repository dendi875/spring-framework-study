package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;

/**
 * X {@link URLConnection} 的实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-14 16:34:46
 */
public class XURLConnection extends URLConnection {

	private final ClassPathResource resource;

	// URL = x:///META-INF/default.properties
	protected XURLConnection(URL url) {
		super(url);
		this.resource = new ClassPathResource(url.getPath());
	}

	@Override
	public void connect() throws IOException {

	}

	public InputStream getInputStream() throws IOException {
		return resource.getInputStream();
	}
}
