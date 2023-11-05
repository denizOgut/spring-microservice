package com.ogutdeniz.flightservice.serializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();
        if (value == null) {
            return null;
        }
        return ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}

