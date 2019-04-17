
package test_jvm.classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器在类相等判断中的影响
 * <p>
 * instanceof关键字
 */
public class TestClassLoader {

    public static void main(String[] args) throws Exception {
        // 自定义类加载器
        ClassLoader myLoader = new ClassLoader(null) {
            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException {
                try {

                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null) {
                        return super.loadClass(fileName);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                } catch (NoClassDefFoundError e) {
                    return super.findClass(name);
                }
            }
        };

        // TestClassLoader.class.newInstance();
        System.out.println("-------TestClassLoader.class.newInstance()");
        Object obj0 = TestClassLoader.class.newInstance();
        Object obj0class = obj0.getClass();
        System.out.println(obj0.getClass());
        System.out.println("obj0class:" + obj0class);
        System.out.println(obj0 instanceof TestClassLoader);
        System.out.println("obj0 class loader:" + obj0.getClass().getClassLoader());
        System.out.println(obj0.hashCode());
        System.out.println(obj0.getClass().hashCode());

        // 使用ClassLoaderTest的类加载器加载本类
        System.out.println("-------使用ClassLoaderTest的类加载器加载本类");
        Object obj1 = TestClassLoader.class.getClassLoader().loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj1class = obj1.getClass();
        System.out.println(obj1.getClass());
        System.out.println("obj1class:" + obj1class);
        System.out.println(obj1 instanceof TestClassLoader);
        System.out.println("obj1 class loader:" + obj1.getClass().getClassLoader());
        System.out.println(obj1.hashCode());
        System.out.println(obj1.getClass().hashCode());

        // 使用SystemClassLoader加载本类
        System.out.println("-------使用SystemClassLoader加载本类");
        Object obj2 = ClassLoader.getSystemClassLoader().loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj2class = obj2.getClass();
        System.out.println(obj2.getClass());
        System.out.println("obj2class:" + obj2class);
        System.out.println(obj2 instanceof TestClassLoader);
        System.out.println("obj2 class loader:" + obj2.getClass().getClassLoader());
        System.out.println(obj2.hashCode());
        System.out.println(obj2.getClass().hashCode());

        // 使用自定义类加载器加载本类
        System.out.println("-------使用自定义类加载器加载本类");
        Object obj3 = myLoader.loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj3class = obj3.getClass();
        System.out.println(obj3.getClass());
        System.out.println("obj3class:" + obj3class);
        System.out.println(obj3 instanceof TestClassLoader);
        System.out.println("obj3 class loader:" + obj3.getClass().getClassLoader());
        System.out.println(obj3.hashCode());
        System.out.println(obj3.getClass().hashCode());
    }
}

