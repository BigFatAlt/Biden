package me.bigfatman.joe.check.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckManifest {

    String name() default "Biden";
    Check.CheckType type() default Check.CheckType.OTHER;
    float maxVl() default 50;
    boolean autoban() default false;
    boolean enabled() default true;


}
