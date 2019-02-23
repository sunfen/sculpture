package cn.sf.sculpture.common.domain.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = false)
public class StringDayConverter implements AttributeConverter<String,Date> {

	@Override
	public Date convertToDatabaseColumn(String attribute) {
		Date date = null;
		if(attribute != null) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(attribute);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	@Override
	public String convertToEntityAttribute(Date dbData) {
		if(dbData == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(dbData);
		return str;
	}
}
