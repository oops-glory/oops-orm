package io.github.pleuvoir.parser;

public interface XmlParser<I> {

	void parser(Class<I> clazz, String xmlName);
}
