package io.github.pleuvoir.parser;

import java.io.InputStream;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import io.github.pleuvoir.dao.UserDao;
import lombok.SneakyThrows;

public class SimpleXmlParser implements XmlParser<UserDao> {

	
	@SneakyThrows
	@Override
	public void parser(Class<UserDao> clazz, String xmlName) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlName);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		// 获得根节点元素
		Element root = doc.getRootElement();
		// 获取所有 select 语句
		List<Element> selectAll = root.elements("select");
		selectAll.forEach(select -> {
			System.out.println(select.getTextTrim());
		});
	}
	
	public static void main(String[] args) {
		new SimpleXmlParser().parser(null, "User.xml");
	}
}
