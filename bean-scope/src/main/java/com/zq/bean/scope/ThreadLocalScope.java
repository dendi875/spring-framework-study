package com.zq.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * ThreadLocal 级别的 Scope
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-07 11:16:06
 */
public class ThreadLocalScope implements Scope {
	public static final String SCOPE_NAME = "thread-local";


	private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope") {

		public Map<String, Object> initialValue() {
			return new HashMap<>();
		}
	};


	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {

		// 非空
		Map<String, Object> context = getContext();

		Object object = context.get(name);

		if (object == null) {
			object = objectFactory.getObject();
			context.put(name, object);
		}

		return object;
	}

	@NonNull
	private Map<String, Object> getContext() {
		return threadLocal.get();
	}

	@Override
	public Object remove(String name) {
		Map<String, Object> context = getContext();
		return context.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO
	}

	@Override
	public Object resolveContextualObject(String key) {
		Map<String, Object> context = getContext();
		return context.get(key);
	}

	@Override
	public String getConversationId() {
		Thread thread = Thread.currentThread();
		return String.valueOf(thread.getId());
	}
}
