package org.apache.sling.models.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {

    Class<?>[] adaptables();

    Class<?>[] adapters() default {};

    DefaultInjectionStrategy defaultInjectionStrategy() default DefaultInjectionStrategy.REQUIRED;

    String condition() default "";

}
