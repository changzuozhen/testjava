package 设计模式_design_pattern.创建型;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_3-%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95%EF%BC%88factory-method%EF%BC%89
 * 3. 工厂方法factory_method（Factory Method）
 * Intent
 * 定义了一个创建对象的接口，但由子类决定要实例化哪个类。工厂方法把实例化操作推迟到子类。
 * <p>
 * Class Diagram
 * 在简单工厂中，创建对象的是另一个类，而在工厂方法中，是由子类来创建对象。
 * <p>
 * 下图中，Factory 有一个 doSomething() 方法，这个方法需要用到一个产品对象，这个产品对象由 factoryMethod() 方法创建。该方法是抽象的，需要由子类去实现。
 */
public class 工厂方法factory_method {
}

abstract class Factory {
    abstract public Product factoryMethod();

    public void doSomething() {
        Product product = factoryMethod();
        // do something with the product
    }
}

class ConcreteFactory extends Factory {
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}

class ConcreteFactory1 extends Factory {
    public Product factoryMethod() {
        return new ConcreteProduct1();
    }
}

class ConcreteFactory2 extends Factory {
    public Product factoryMethod() {
        return new ConcreteProduct2();
    }
}