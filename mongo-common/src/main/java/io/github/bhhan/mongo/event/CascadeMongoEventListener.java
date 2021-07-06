package io.github.bhhan.mongo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

import java.util.Objects;

import static org.springframework.util.ReflectionUtils.doWithFields;

@RequiredArgsConstructor
public class CascadeMongoEventListener extends AbstractMongoEventListener<Object> {
    private final MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        Object id = Objects.requireNonNull(event.getDocument()).get("_id");
        Class<Object> objectClass = Objects.requireNonNull(event.getType());

        if(objectClass.isAnnotationPresent(Aggregate.class)){
            Object deleteObject = mongoOperations.findById(id, objectClass);
            doWithFields(objectClass, new CascadeDeleteCallback(deleteObject, mongoOperations));
        }
    }
}
