package task01;

import java.lang.annotation.*;

/**
 * Created by konstantin on 23.02.2020.
 */
@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface MyAnnotation {
    int value() default 10;
}
