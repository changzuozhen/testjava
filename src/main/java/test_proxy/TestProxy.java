package test_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {


    interface Developer {
        void code();

        void debug();
    }

    private static class JavaDeveloper implements Developer {

        private String name;

        public JavaDeveloper(String name) {
            this.name = name;
        }

        @Override
        public void code() {
            System.out.println(name + " is coding");
        }

        @Override
        public void debug() {
            System.out.println(name + " is debugging");
        }
    }

    public static void main(String[] args) {

        JavaDeveloper jack = new JavaDeveloper("Jack");
        jack.code();
        jack.debug();

        System.out.println("--------------");

        JavaDeveloper jone = new JavaDeveloper("Jone");

        Developer joneProxy = (Developer) Proxy.newProxyInstance(jone.getClass().getClassLoader(), jone.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("code".equals(method.getName())) {
                    System.out.println("Jone is praying for the code!");
                }
                if ("debug".equals(method.getName())) {
                    System.out.println("Jone's have no bug!No need to debug!");
                }
//                return null;
                return method.invoke(jone, args);
            }
        });
        joneProxy.code();
        joneProxy.debug();

    }

}
