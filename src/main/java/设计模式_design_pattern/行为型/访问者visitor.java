package 设计模式_design_pattern.行为型;

import utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 11. 访问者（Visitor）
 * Intent
 * 为一个对象结构（比如组合结构）增加新能力。
 * <p>
 * Class Diagram
 * Visitor：访问者，为每一个 ConcreteElement 声明一个 visit 操作
 * ConcreteVisitor：具体访问者，存储遍历过程中的累计结果
 * ObjectStructure：对象结构，可以是组合结构，或者是一个集合。
 * https://gitee.com/CyC2018/CS-Notes/raw/master/docs/pics/ec923dc7-864c-47b0-a411-1f2c48d084de.png
 */
public class 访问者visitor {
    public static void main(String[] args) {
        Customer customer1 = new Customer("customer1");
        customer1.addOrder(new Order("order1", "item1"));
        customer1.addOrder(new Order("order2", "item1"));
        customer1.addOrder(new Order("order3", "item1"));

        Order order = new Order("order_a");
        order.addItem(new Item("item_a1"));
        order.addItem(new Item("item_a2"));
        order.addItem(new Item("item_a3"));
        Customer customer2 = new Customer("customer2");
        customer2.addOrder(order);

        CustomerGroup customers = new CustomerGroup();
        customers.addCustomer(customer1);
        customers.addCustomer(customer2);

        GeneralReport visitor = new GeneralReport();
        LogUtils.i("---------");
        customers.accept(visitor);
        LogUtils.i("---------");
        visitor.displayResults();
    }


}

interface Element {
    void accept(Visitor visitor);
}

class CustomerGroup {

    private List<Customer> customers = new ArrayList<>();

    void accept(Visitor visitor) {
        for (Customer customer : customers) {
            customer.accept(visitor);
        }
    }

    void addCustomer(Customer customer) {
        customers.add(customer);
    }
}

class Customer implements Element {

    private String name;
    private List<Order> orders = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void addOrder(Order order) {
        orders.add(order);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }
}

class Order implements Element {

    private String name;
    private List<Item> items = new ArrayList();

    Order(String name) {
        this.name = name;
    }

    Order(String name, String itemName) {
        this.name = name;
        this.addItem(new Item(itemName));
    }

    String getName() {
        return name;
    }

    void addItem(Item item) {
        items.add(item);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);

        for (Item item : items) {
            item.accept(visitor);
        }
    }
}

class Item implements Element {

    private String name;

    Item(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}

class GeneralReport implements Visitor {

    private int customersNo;
    private int ordersNo;
    private int itemsNo;

    public void visit(Customer customer) {
        LogUtils.d(customer.getName());
        customersNo++;
    }

    public void visit(Order order) {
        LogUtils.d(order.getName());
        ordersNo++;
    }

    public void visit(Item item) {
        LogUtils.d(item.getName());
        itemsNo++;
    }

    public void displayResults() {
        LogUtils.d("Number of customers: " + customersNo);
        LogUtils.d("Number of orders:    " + ordersNo);
        LogUtils.d("Number of items:     " + itemsNo);
    }
}
