package io.github.pleuvoir.parser;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class XmlMetaData {

	List<Select> selects;
}
