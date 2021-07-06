package io.github.bhhan.mongo.event;

import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;

import static org.springframework.util.ReflectionUtils.FieldCallback;
import static org.springframework.util.ReflectionUtils.makeAccessible;

public class IdentifierCallback implements FieldCallback {
    private boolean idFound;

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        makeAccessible(field);

        if(field.isAnnotationPresent(Id.class)){
            idFound = true;
        }
    }

    public boolean isIdFound() {
        return idFound;
    }
}
