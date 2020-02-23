package task01;

/**
 * Created by konstantin on 23.02.2020.
 */
@MyAnnotation(40)
public class AbstractParent {
    protected   String name;

    public AbstractParent(String name) {
        this.name = name;
    }

    @MyAnnotation(40)
    public String getName() {
        return name;
    }
}
