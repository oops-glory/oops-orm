package io.github.pleuvoir.handler;

import java.lang.reflect.Method;
import java.util.Map;

public interface SqlHandler {

	void excuteSql(Method method, Map<String, Object> params);

}
