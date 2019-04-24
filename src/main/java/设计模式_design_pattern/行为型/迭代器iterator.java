package 设计模式_design_pattern.行为型;

import utils.LogUtils;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_4-%E8%BF%AD%E4%BB%A3%E5%99%A8%EF%BC%88iterator%EF%BC%89
 * 4. 迭代器（Iterator）
 * Intent
 * 提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示。
 * <p>
 * Class Diagram
 * Aggregate 是聚合类，其中 createIterator() 方法可以产生一个 Iterator；
 * Iterator 主要定义了 hasNext() 和 next() 方法。
 * Client 组合了 Aggregate，为了迭代遍历 Aggregate，也需要组合 Iterator。
 */
public class 迭代器iterator {

    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            LogUtils.d(iterator.next());
        }
    }
}

interface Aggregate {
    Iterator createIterator();
}

class ConcreteAggregate implements Aggregate {

    private Integer[] items;

    public ConcreteAggregate() {
        items = new Integer[10];
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator<Integer>(items);
    }
}

interface Iterator<Item> {

    Item next();

    boolean hasNext();
}

class ConcreteIterator<Item> implements Iterator {

    private Item[] items;
    private int position = 0;

    public ConcreteIterator(Item[] items) {
        this.items = items;
    }

    @Override
    public Object next() {
        return items[position++];
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }
}
