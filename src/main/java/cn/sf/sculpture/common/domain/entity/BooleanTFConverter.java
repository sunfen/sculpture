package cn.sf.sculpture.common.domain.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=false)
public class BooleanTFConverter implements AttributeConverter<Boolean, String> {
	@Override
	public String convertToDatabaseColumn(Boolean value) {
		if (Boolean.TRUE.equals(value)) {
			return "T";
		} else {
			return "F";
		}
	}
	
	@Override
	public Boolean convertToEntityAttribute(String value) {
		return "T".equals(value);
	}
}
