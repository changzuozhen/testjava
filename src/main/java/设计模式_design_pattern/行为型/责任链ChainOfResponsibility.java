package 设计模式_design_pattern.行为型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=%E4%B8%89%E3%80%81%E8%A1%8C%E4%B8%BA%E5%9E%8B
 * 1. 责任链（Chain Of Responsibility）
 * Intent
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。
 * <p>
 * Class Diagram
 * Handler：定义处理请求的接口，并且实现后继链（successor）
 */
class 责任链ChainOfResponsibility {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);
        Handler handler2 = new ConcreteHandler2(handler1);

        Request request1 = new Request(RequestType.TYPE1, "request1");
        handler2.handleRequest(request1);

        Request request2 = new Request(RequestType.TYPE2, "request2");
        handler2.handleRequest(request2);
    }
}

abstract class Handler {

    protected Handler successor;


    public Handler(Handler successor) {
        this.successor = successor;
    }


    protected abstract void handleRequest(Request request);
}

class ConcreteHandler1 extends Handler {

    public ConcreteHandler1(Handler successor) {
        super(successor);
    }


    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE1) {
            LogUtils.d(request.getName() + " is handle by ConcreteHandler1");
            return;
        }
        if (successor != null) {
            LogUtils.d("successor: request = [" + request + "]");
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {

    public ConcreteHandler2(Handler successor) {
        super(successor);
    }


    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            LogUtils.d(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        if (successor != null) {
            LogUtils.d("successor: request = [" + request + "]");
            successor.handleRequest(request);
        }
    }
}

class Request {

    private RequestType type;
    private String name;


    public Request(RequestType type, String name) {
        this.type = type;
        this.name = name;
    }


    public RequestType getType() {
        return type;
    }


    public String getName() {
        return name;
    }
}

enum RequestType {
    TYPE1, TYPE2
}
