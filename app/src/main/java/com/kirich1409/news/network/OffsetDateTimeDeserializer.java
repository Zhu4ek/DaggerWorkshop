package com.kirich1409.news.network;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.threeten.bp.OffsetDateTime;

import java.io.IOException;

/**
 * @author Kirill Rozov
 */

public class OffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

    public OffsetDateTimeDeserializer() {
        super(OffsetDateTime.class);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser,
                                      DeserializationContext deserializationContext)
            throws IOException {
        String value = jsonParser.getCodec().readValue(jsonParser, String.class);
        if (value == null) {
            return null;
        } else {
            try {
                return OffsetDateTime.parse(value);
            } catch (Exception ignored) {
                if (!value.endsWith("Z")) {
                    value += "Z";
                }
                return OffsetDateTime.parse(value);
            }
        }
    }
}
