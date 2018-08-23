package io.github.pleuvoir.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import io.github.pleuvoir.annotation.Param;
import io.github.pleuvoir.kit.JdbcHelper;
import io.github.pleuvoir.parser.Select;
import io.github.pleuvoir.parser.SimpleXmlParser;
import io.github.pleuvoir.parser.XmlMetaData;
import io.github.pleuvoir.parser.XmlParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleSqlHandler implements SqlHandler {

	private XmlParser xmlParser = new SimpleXmlParser();

	@Override
	public void excuteSql(Method method, Map<String, Object> params) {

		XmlMetaData xmlMetaData = xmlParser.parser(method.getDeclaringClass().getSimpleName().concat("Mapper.xml"));

		Optional<Select> ops = xmlMetaData.getSelects().parallelStream().filter(s -> s.getId().equals(method.getName())).findFirst();
		if (ops.isPresent()) {
			
			Select select = ops.get();
			String sql 	  = select.getSql();

			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (Annotation[] annotations : parameterAnnotations) {
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().isAssignableFrom(Param.class)) {
						Param p = (Param) annotation;
						String paramName = p.value();
						String paramValue = (String) params.get(paramName);
						String replacedSql = sql.replace("#{".concat(paramName).concat("}"),"'".concat(paramValue).concat("'"));
						print(replacedSql);
					}
				}
			}
		}
	}

	private void print(String sql) {
		List<Map<String, Object>> result = JdbcHelper.executeSql(sql);
		for (Map<String, Object> map : result) {
			map.entrySet().forEach(k -> {
				log.info(k.getKey() + " = " + k.getValue());
			});
		}
	}
}
