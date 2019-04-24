package 设计模式_design_pattern.创建型.抽象工厂;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_4-%E6%8A%BD%E8%B1%A1%E5%B7%A5%E5%8E%82%EF%BC%88abstract-factory%EF%BC%89
 * 4. 抽象工厂abstract_factory（Abstract Factory）
 * Intent
 * 提供一个接口，用于创建 相关的对象家族 。
 * <p>
 * Class Diagram
 * 抽象工厂模式创建的是对象家族，也就是很多对象而不是一个对象，并且这些对象是相关的，也就是说必须一起创建出来。而工厂方法模式只是用于创建一个对象，这和抽象工厂模式有很大不同。
 * <p>
 * 抽象工厂模式用到了工厂方法模式来创建单一对象，AbstractFactory 中的 createProductA() 和 createProductB() 方法都是让子类来实现，这两个方法单独来看就是在创建一个对象，这符合工厂方法模式的定义。
 * <p>
 * 至于创建对象的家族这一概念是在 Client 体现，Client 要通过 AbstractFactory 同时调用两个方法来创建出两个对象，在这里这两个对象就有很大的相关性，Client 需要同时创建出这两个对象。
 * <p>
 * 从高层次来看，抽象工厂使用了组合，即 Cilent 组合了 AbstractFactory，而工厂方法模式使用了继承。
 */
public class 抽象工厂abstract_factory {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        AbstractProductA productA = abstractFactory.createProductA();
        AbstractProductB productB = abstractFactory.createProductB();

        abstractFactory = new ConcreteFactory2();
        productA = abstractFactory.createProductA();
        productB = abstractFactory.createProductB();
        // do something with productA and productB
    }
}

class AbstractProductA {
}

class AbstractProductB {
}

class ProductA1 extends AbstractProductA {
}

class ProductA2 extends AbstractProductA {
}

class ProductB1 extends AbstractProductB {
}

class ProductB2 extends AbstractProductB {
}

abstract class AbstractFactory {
    abstract AbstractProductA createProductA();

    abstract AbstractProductB createProductB();
}

class ConcreteFactory1 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA1();
    }

    AbstractProductB createProductB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA2();
    }

    AbstractProductB createProductB() {
        return new ProductB2();
    }
}
