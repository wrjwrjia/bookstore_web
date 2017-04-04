package annotation;

/**
 * Created by jia on 3/23/17.
 */
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface table {
    public String tableName();
}
