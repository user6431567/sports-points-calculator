package com.testprojinc.dpcalc.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType attribute) {
        if (null == attribute) {
            return null;
        }
        return attribute.getType();
    }

    @Override
    public EventType convertToEntityAttribute(String dbData) {
        return EventType.fromString(dbData);
    }

}
