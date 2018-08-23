package io.github.pleuvoir.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import lombok.SneakyThrows;

public class SimpleXmlParser implements XmlParser {

	@SneakyThrows
	@Override
	public XmlMetaData parser(String xmlName) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlName);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		// 获得根节点元素
		Element root = doc.getRootElement();
		// 获取所有 select 语句
		List<Element> selectAll = root.elements("select");
		List<Select> selects = new ArrayList<>();
		selectAll.forEach(select -> {
			Select s = Select.builder().sql(select.getTextTrim()).id(select.attribute("id").getValue()).build();
			selects.add(s);
		});
		return XmlMetaData.builder().selects(selects).build();
	}

}
