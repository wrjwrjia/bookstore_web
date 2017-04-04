package annotation;

/**
 * Created by jia on 3/23/17.
 */
import java.lang.annotation.*;

@Target(ElementType.FIELD)    //annotation target
@Retention(RetentionPolicy.RUNTIME)

public @interface column {
    public String field();    //name of field
    public boolean primaryKey() default false;  //is it primary key
    public String type() default "VARCHAR(80)";  //field type
    public boolean defaultNull() default true;  //is it allowed to be null
}
