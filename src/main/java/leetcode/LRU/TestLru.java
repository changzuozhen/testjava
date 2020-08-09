package leetcode.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

class TestLru {

    static class LRUcache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUcache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public Integer put(Integer key, Integer value) {
            return super.put(key, value);
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUcache lrUcache = new LRUcache(2);
        System.out.println("put(1, 1):" + lrUcache.put(1, 1));
        System.out.println("get(2):" + lrUcache.get(2));
        System.out.println("get(1):" + lrUcache.get(1));

        System.out.println("put(2, 2):" + lrUcache.put(2, 2));
        System.out.println("get(2):" + lrUcache.get(2));
        System.out.println("get(1):" + lrUcache.get(1));

        System.out.println("put(3, 3):" + lrUcache.put(3, 3));
        System.out.println("get(2):" + lrUcache.get(2));
        System.out.println("get(1):" + lrUcache.get(1));


    }
}
