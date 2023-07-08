package org.soujava;

import org.bson.types.ObjectId;
import org.eclipse.jnosql.mapping.AttributeConverter;

public class ObjectIdConverter implements AttributeConverter<String, ObjectId> {

    @Override
    public ObjectId convertToDatabaseColumn(String attribute) {
        if (attribute != null && !attribute.isBlank()) {
            return new ObjectId(attribute);
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(ObjectId dbData) {
        if (dbData != null) {
            return dbData.toHexString();
        }
        return null;
    }
}