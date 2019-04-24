package 设计模式_design_pattern.创建型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_6-%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F%EF%BC%88prototype%EF%BC%89
 * 6. 原型模式prototype（Prototype）
 * Intent
 * 使用原型实例指定要创建对象的类型，通过复制这个原型来创建新对象。
 */
public class 原型模式prototype {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype("abc");
        Prototype clone = prototype.myClone();
        LogUtils.d(clone.toString());
    }
}

abstract class Prototype {
    abstract Prototype myClone();
}

class ConcretePrototype extends Prototype {

    private String filed;

    public ConcretePrototype(String filed) {
        this.filed = filed;
    }

    @Override
    Prototype myClone() {
        return new ConcretePrototype(filed);
    }

    @Override
    public String toString() {
        return filed;
    }
}