package 设计模式_design_pattern.行为型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_10-%E6%A8%A1%E6%9D%BF%E6%96%B9%E6%B3%95%EF%BC%88template-method%EF%BC%89
 * 10. 模板方法（Template Method）
 * https://gitee.com/CyC2018/CS-Notes/raw/master/docs/pics/c3c1c0e8-3a78-4426-961f-b46dd0879dd8.png
 * Intent
 * 定义算法框架，并将一些步骤的实现延迟到子类。
 * 通过模板方法，子类可以重新定义算法的某些步骤，而不用改变算法的结构。
 * <p>
 * Implementation
 * 冲咖啡和冲茶都有类似的流程，但是某些步骤会有点不一样，要求复用那些相同步骤的代码。
 */
public class 模板方法template_method {
    public static void main(String[] args) {
        CaffeineBeverage caffeineBeverage = new Coffee();
        caffeineBeverage.prepareRecipe();
        LogUtils.d("-----------");
        caffeineBeverage = new Tea();
        caffeineBeverage.prepareRecipe();
    }
}

abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        LogUtils.d("boilWater");
    }

    void pourInCup() {
        LogUtils.d("pourInCup");
    }
}

class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        LogUtils.d("Coffee.brew");
    }

    @Override
    void addCondiments() {
        LogUtils.d("Coffee.addCondiments");
    }
}

class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        LogUtils.d("Tea.brew");
    }

    @Override
    void addCondiments() {
        LogUtils.d("Tea.addCondiments");
    }
}

