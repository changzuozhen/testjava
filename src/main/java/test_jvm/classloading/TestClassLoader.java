package test_jvm.classloading;

import utils.LogUtils;

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
        LogUtils.d("-------TestClassLoader.class.newInstance()");
        Object obj0 = TestClassLoader.class.newInstance();
        Object obj0class = obj0.getClass();
        LogUtils.d(obj0.getClass());
        LogUtils.d("obj0class:" + obj0class);
        LogUtils.d(obj0 instanceof TestClassLoader);
        LogUtils.d("obj0 class loader:" + obj0.getClass().getClassLoader());
        LogUtils.d(obj0.hashCode());
        LogUtils.d(obj0.getClass().hashCode());

        // 使用ClassLoaderTest的类加载器加载本类
        LogUtils.d("-------使用ClassLoaderTest的类加载器加载本类");
        Object obj1 = TestClassLoader.class.getClassLoader().loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj1class = obj1.getClass();
        LogUtils.d(obj1.getClass());
        LogUtils.d("obj1class:" + obj1class);
        LogUtils.d(obj1 instanceof TestClassLoader);
        LogUtils.d("obj1 class loader:" + obj1.getClass().getClassLoader());
        LogUtils.d(obj1.hashCode());
        LogUtils.d(obj1.getClass().hashCode());

        // 使用SystemClassLoader加载本类
        LogUtils.d("-------使用SystemClassLoader加载本类");
        Object obj2 = ClassLoader.getSystemClassLoader().loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj2class = obj2.getClass();
        LogUtils.d(obj2.getClass());
        LogUtils.d("obj2class:" + obj2class);
        LogUtils.d(obj2 instanceof TestClassLoader);
        LogUtils.d("obj2 class loader:" + obj2.getClass().getClassLoader());
        LogUtils.d(obj2.hashCode());
        LogUtils.d(obj2.getClass().hashCode());

        // 使用自定义类加载器加载本类
        LogUtils.d("-------使用自定义类加载器加载本类");
        Object obj3 = myLoader.loadClass("test_jvm.classloading.TestClassLoader").newInstance();
        Object obj3class = obj3.getClass();
        LogUtils.d(obj3.getClass());
        LogUtils.d("obj3class:" + obj3class);
        LogUtils.d(obj3 instanceof TestClassLoader);
        LogUtils.d("obj3 class loader:" + obj3.getClass().getClassLoader());
        LogUtils.d(obj3.hashCode());
        LogUtils.d(obj3.getClass().hashCode());
    }
}

