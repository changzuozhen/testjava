package test_jvm.集合框架;

import java.util.concurrent.ConcurrentHashMap;

class TestConcurrentHashMap {
    public static void main(String[] args) {
        testConcurrentHashMapAPI();
    }

    public static void testConcurrentHashMapAPI() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.putIfAbsent("key2", 3);
        map.putIfAbsent("key3", 3);
        System.out.println("Initial Map: " + map);
        map.remove("key1");
        System.out.println("After removing key1: " + map);
        map.replace("key2", 4);
        System.out.println("After replacing key2: " + map);
        Integer value = map.getOrDefault("key5", 5);
        System.out.println("Get key5: " + value);
    }
}
