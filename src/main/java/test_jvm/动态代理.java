package test_jvm;

import utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class 动态代理 {

    public static void main(String[] args) {
        Star star = new RealStar();
        StarHandler handler = new StarHandler(star);
        Star starProxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, handler);
        starProxy.signContract();
        starProxy.sing();
        starProxy.getMoney();

        star.signContract();
        star.sing();
        star.getMoney();
    }

    public interface Star {
        void confer();

        void signContract();

        void sing();

        void getMoney();
    }

    public static class RealStar implements Star {
        public void confer() {
            LogUtils.d("RealStar.confer()");
        }

        public void signContract() {
            LogUtils.d("RealStar.signContract()");
        }

        public void sing() {
            LogUtils.d("RealStar.sing()");
        }

        public void getMoney() {
            LogUtils.d("RealStar.getMoney()");
        }
    }

    public static class StarHandler implements InvocationHandler {
        Star star;

        public StarHandler(Star realStar) {
            super();
            this.star = realStar;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("sing")) {
                method.invoke(star, args);
            } else {
                LogUtils.d(method.getName());
            }
            return null;
        }
    }
}
