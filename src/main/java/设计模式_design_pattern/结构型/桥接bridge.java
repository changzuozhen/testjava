package 设计模式_design_pattern.结构型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_2-%E6%A1%A5%E6%8E%A5%EF%BC%88bridge%EF%BC%89
 * 2. 桥接（Bridge）
 * Intent
 * 将抽象与实现分离开来，使它们可以独立变化。
 * <p>
 * Class Diagram
 * Abstraction：定义抽象类的接口
 * Implementor：定义实现类接口
 * <p>
 * <p>
 * Implementation
 * RemoteControl 表示遥控器，指代 Abstraction。
 * <p>
 * TV 表示电视，指代 Implementor。
 * <p>
 * 桥接模式将遥控器和电视分离开来，从而可以独立改变遥控器或者电视的实现。
 */
class 桥接bridge {
    public static void main(String[] args) {
        RemoteControl remoteControl1 = new ConcreteRemoteControl1(new RCA());
        remoteControl1.on();
        remoteControl1.off();
        remoteControl1.tuneChannel();
        RemoteControl remoteControl2 = new ConcreteRemoteControl2(new Sony());
        remoteControl2.on();
        remoteControl2.off();
        remoteControl2.tuneChannel();
    }
}

abstract class TV {
    public abstract void on();

    public abstract void off();

    public abstract void tuneChannel();
}

class Sony extends TV {
    @Override
    public void on() {
        LogUtils.d("Sony.on()");
    }

    @Override
    public void off() {
        LogUtils.d("Sony.off()");
    }

    @Override
    public void tuneChannel() {
        LogUtils.d("Sony.tuneChannel()");
    }
}

class RCA extends TV {
    @Override
    public void on() {
        LogUtils.d("RCA.on()");
    }

    @Override
    public void off() {
        LogUtils.d("RCA.off()");
    }

    @Override
    public void tuneChannel() {
        LogUtils.d("RCA.tuneChannel()");
    }
}

abstract class RemoteControl {
    protected TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    public abstract void on();

    public abstract void off();

    public abstract void tuneChannel();
}

class ConcreteRemoteControl1 extends RemoteControl {
    public ConcreteRemoteControl1(TV tv) {
        super(tv);
    }

    @Override
    public void on() {
        LogUtils.d("ConcreteRemoteControl1.on()");
        tv.on();
    }

    @Override
    public void off() {
        LogUtils.d("ConcreteRemoteControl1.off()");
        tv.off();
    }

    @Override
    public void tuneChannel() {
        LogUtils.d("ConcreteRemoteControl1.tuneChannel()");
        tv.tuneChannel();
    }
}

class ConcreteRemoteControl2 extends RemoteControl {
    public ConcreteRemoteControl2(TV tv) {
        super(tv);
    }

    @Override
    public void on() {
        LogUtils.d("ConcreteRemoteControl2.on()");
        tv.on();
    }

    @Override
    public void off() {
        LogUtils.d("ConcreteRemoteControl2.off()");
        tv.off();
    }

    @Override
    public void tuneChannel() {
        LogUtils.d("ConcreteRemoteControl2.tuneChannel()");
        tv.tuneChannel();
    }
}

