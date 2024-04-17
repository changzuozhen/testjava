package test_jvm.基础语法;

import utils.LogUtils;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

class TestClassloader {
    public static void main(String[] args) {
        ClassLoader classLoader = TestClassloader.class.getClassLoader();
        do {
            loaderInfo(classLoader);
            classLoader = classLoader.getParent();
        } while (classLoader != null);

//        sun.misc.Launcher
//                getBootstrapClassPath
//
//                URLClassPath.getURLs(

        loaderInfo(ArrayList.class.getClassLoader());
    }

    private static void loaderInfo(ClassLoader classLoader) {
        LogUtils.d("⚠️classloader:" + classLoader);
        logoutURLS(((URLClassLoader) classLoader).getURLs());
    }

    private static void logoutURLS(URL[] urls) {
        for (URL urL : urls) {
            LogUtils.d("⚠️ url = [" + urL + "]");
        }
    }
}
