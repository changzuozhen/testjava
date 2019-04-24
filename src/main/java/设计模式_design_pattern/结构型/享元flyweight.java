package 设计模式_design_pattern.结构型;

import utils.LogUtils;

import java.util.HashMap;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_6-%E4%BA%AB%E5%85%83%EF%BC%88flyweight%EF%BC%89
 * 6. 享元（Flyweight）
 * Intent
 * 利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的。
 * <p>
 * Class Diagram
 * Flyweight：享元对象
 * IntrinsicState：内部状态，享元对象共享内部状态
 * ExtrinsicState：外部状态，每个享元对象的外部状态不同
 */
public class 享元flyweight {
}

interface Flyweight {
    void doOperation(String extrinsicState);
}

class ConcreteFlyweight implements Flyweight {

    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void doOperation(String extrinsicState) {
        LogUtils.i("-----");
        LogUtils.d("Object address: " + System.identityHashCode(this));
        LogUtils.d("IntrinsicState: " + intrinsicState);
        LogUtils.d("ExtrinsicState: " + extrinsicState);
    }
}

class FlyweightFactory {

    private HashMap<String, Flyweight> flyweights = new HashMap<>();

    Flyweight getFlyweight(String intrinsicState) {
        if (!flyweights.containsKey(intrinsicState)) {
            Flyweight flyweight = new ConcreteFlyweight(intrinsicState);
            flyweights.put(intrinsicState, flyweight);
        }
        return flyweights.get(intrinsicState);
    }
}

class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("aa");
        Flyweight flyweight2 = factory.getFlyweight("aa");
        flyweight1.doOperation("x");
        flyweight2.doOperation("y");
    }
}