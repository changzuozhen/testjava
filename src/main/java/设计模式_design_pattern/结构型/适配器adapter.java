package 设计模式_design_pattern.结构型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_1-%E9%80%82%E9%85%8D%E5%99%A8%EF%BC%88adapter%EF%BC%89
 * 1. 适配器（Adapter）
 * Intent
 * 把一个类接口转换成另一个用户需要的接口。
 * Implementation
 * 鸭子（Duck）和火鸡（Turkey）拥有不同的叫声，Duck 的叫声调用 quack() 方法，而 Turkey 调用 gobble() 方法。
 * <p>
 * 要求将 Turkey 的 gobble() 方法适配成 Duck 的 quack() 方法，从而让火鸡冒充鸭子！
 */
class 适配器adapter {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}

interface Duck {
    void quack();
}

interface Turkey {
    void gobble();
}

class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        LogUtils.d("gobble!");
    }
}

class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}