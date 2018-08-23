package io.github.pleuvoir.sample;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import io.github.pleuvoir.handler.SimpleSqlHandler;
import io.github.pleuvoir.handler.SqlHandler;
import io.github.pleuvoir.sample.dao.UserDao;

public class Client {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		SqlHandler handler = new SimpleSqlHandler();
		Method selectAll = UserDao.class.getDeclaredMethod("selectByName", String.class);
		Map<String, Object> map = new HashMap<>();
		map.put("ename", "JONES");
		handler.excuteSql(selectAll, map);
	}
	
}
