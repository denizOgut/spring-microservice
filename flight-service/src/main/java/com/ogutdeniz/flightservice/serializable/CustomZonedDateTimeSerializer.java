package com.ogutdeniz.flightservice.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            String formattedValue = value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
            gen.writeString(formattedValue);
        }
    }
}
