package test_jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestFinal {

    public static void modify(Object object, String fieldName, Object newFieldValue) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true); //Field 的 modifiers 是私有的
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(object, newFieldValue);
    }

    /**
     * 只要不会被编译器内联优化的 final 属性就可以通过反射有效的进行修改
     */
    public static void main(String[] args) throws Exception {
        Person jordan = new Person("Chicago");

        modify(jordan, "firstName", "Michael");
        System.out.println(jordan.firstName); //Mike

        modify(jordan, "lastName", "Michael");
        System.out.println(jordan.lastName); //Michael

        modify(jordan, "age", 51f);
        System.out.println(jordan.age); //50.5

        modify(jordan, "height", 1.98f);
        System.out.println(jordan.height); //1.98

        modify(jordan, "address", new Address("ccc", "ddd"));
        System.out.println(jordan.address.line1); //ccc

        modify(jordan, "city", "Miami");
        System.out.println(jordan.city); //Miami
    }
}

class Person {
    public final String firstName = "Mike";
    public final String lastName = new String("Jordan"); //可被有效修改
    public final float age = 50.5f;
    public final Float height = 1.99f; //可被有效修改
    public final Address address = new Address("aaa", "bbb"); //可被有效修改

    public final String city; //可被有效修改

    public Person(String city) {
        this.city = city;
    }
}

final class Address {
    public final String line1;
    public final String line2;

    public Address(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }
}