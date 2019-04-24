package 设计模式_design_pattern.行为型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_8-%E7%8A%B6%E6%80%81%EF%BC%88state%EF%BC%89
 * 8. 状态（State）
 * Intent
 * 允许对象在内部状态改变时改变它的行为，对象看起来好像修改了它所属的类。
 * <p>
 * Class Diagram
 * <p>
 * Implementation
 * 糖果销售机有多种状态，每种状态下销售机有不同的行为，状态可以发生转移，使得销售机的行为也发生改变。
 */
public class 状态state {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();

        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
    }
}

interface State {
    /**
     * 投入 25 分钱
     */
    void insertQuarter();

    /**
     * 退回 25 分钱
     */
    void ejectQuarter();

    /**
     * 转动曲柄
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}

class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        LogUtils.d("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        LogUtils.d("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        LogUtils.d("You turned...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        LogUtils.d("No gumball dispensed");
    }
}

class NoQuarterState implements State {

    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        LogUtils.d("You insert a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        LogUtils.d("You haven't insert a quarter");
    }

    @Override
    public void turnCrank() {
        LogUtils.d("You turned, but there's no quarter");
    }

    @Override
    public void dispense() {
        LogUtils.d("You need to pay first");
    }
}

class SoldOutState implements State {

    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        LogUtils.d("You can't insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuarter() {
        LogUtils.d("You can't eject, you haven't inserted a quarter yet");
    }

    @Override
    public void turnCrank() {
        LogUtils.d("You turned, but there are no gumballs");
    }

    @Override
    public void dispense() {
        LogUtils.d("No gumball dispensed");
    }
}

class SoldState implements State {

    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        LogUtils.d("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        LogUtils.d("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        LogUtils.d("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            LogUtils.d("Oops, out of gumballs");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

class GumballMachine {

    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;

    private State state;
    private int count = 0;

    public GumballMachine(int numberGumballs) {
        count = numberGumballs;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public void insertQuarter() {
        LogUtils.log("insertQuarter() called", 2, 2);
        state.insertQuarter();
    }

    public void ejectQuarter() {
        LogUtils.log("ejectQuarter() called", 2, 2);
        state.ejectQuarter();
    }

    public void turnCrank() {
        LogUtils.log("turnCrank() called", 2, 2);
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        LogUtils.d("A gumball comes rolling out the slot...");
        if (count != 0) {
            count -= 1;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }
}
