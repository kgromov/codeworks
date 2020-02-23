package task01;

import java.lang.reflect.Method;

/**
 * Created by konstantin on 23.02.2020.
 */
//@MyAnnotation(20)
public class Child extends AbstractParent {
    private int age;

    public Child(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        AbstractParent parent = new AbstractParent("John Dou");
        Child child = new Child("John Dou", 20);
        MyAnnotation myAnnotation = parent.getClass().getAnnotation(MyAnnotation.class);
        MyAnnotation methodAnnotation = parent.getClass().getMethod("getName").getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation.value());
        if (child.getClass().isAnnotationPresent(MyAnnotation.class))
        {
            System.out.println(child.getClass().getAnnotation(MyAnnotation.class).value());
        }
    }
}
