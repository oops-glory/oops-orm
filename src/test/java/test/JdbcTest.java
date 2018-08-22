package test;

import java.util.List;
import java.util.Map;
import io.github.pleuvoir.kit.JdbcHelper;

public class JdbcTest {

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				List<Map<String, Object>> executeSql = JdbcHelper.executeSql("select * from emp");

				for (Map<String, Object> map : executeSql) {
					map.entrySet().forEach(k -> {
						System.out.println(Thread.currentThread().getName() + "||" + k.getKey() + "==" + k.getValue());
					});
				}
			}
		};
		
		for (int i = 0; i < 5000; i++) {
			new Thread(runnable).start();
		}
	}
	
}
