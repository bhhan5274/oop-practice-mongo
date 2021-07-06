package io.github.bhhan.mongo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

import static org.springframework.util.ReflectionUtils.*;

@RequiredArgsConstructor
public class CascadeDeleteCallback implements FieldCallback {
    private final Object source;
    private final MongoOperations mongoOperations;

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        makeAccessible(field);

        if(field.isAnnotationPresent(DBRef.class) &&
            field.isAnnotationPresent(Cascade.class)){
            final Object fieldValue = field.get(source);

            if(Objects.nonNull(fieldValue)){
                CascadeType cascadeType = field.getAnnotation(Cascade.class).value();

                if(cascadeType.equals(CascadeType.DELETE) ||
                    cascadeType.equals(CascadeType.ALL)){
                    if(fieldValue instanceof Collection<?>){
                        ((Collection<?>) fieldValue)
                                .forEach(this::cascadeDelete);
                    }else {
                        cascadeDelete(fieldValue);
                    }
                }
            }
        }
    }

    private void cascadeDelete(Object fieldValue){
        IdentifierCallback identifierCallback = new IdentifierCallback();
        doWithFields(fieldValue.getClass(), identifierCallback);

        if(identifierCallback.isIdFound()){
            mongoOperations.remove(fieldValue);
        }
    }
}
