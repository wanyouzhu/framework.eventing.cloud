package top.vanzhu.framework.eventing.cloud;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@StreamListener
public @interface ExternalEventHandler {
    @AliasFor(annotation = StreamListener.class, attribute = "condition")
    String value() default "";

    @AliasFor(annotation = StreamListener.class, attribute = "target")
    String target() default Sink.INPUT;

    @AliasFor(annotation = StreamListener.class, attribute = "condition")
    String condition() default "";
}
