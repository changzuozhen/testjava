package 设计模式_design_pattern.结构型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_5-%E5%A4%96%E8%A7%82%EF%BC%88facade%EF%BC%89
 * 5. 外观（Facade）
 * Intent
 * 提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。
 * <p>
 * Class Diagram
 * <p>
 * <p>
 * Implementation
 * 观看电影需要操作很多电器，使用外观模式实现一键看电影功能。
 */
public class 外观facade {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.watchMovie();
    }
}

class SubSystem {
    public void turnOnTV() {
        LogUtils.d("turnOnTV()");
    }

    public void setCD(String cd) {
        LogUtils.d("setCD( " + cd + " )");
    }

    public void startWatching() {
        LogUtils.d("startWatching()");
    }
}

class Facade {
    private SubSystem subSystem = new SubSystem();

    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.startWatching();
    }
}