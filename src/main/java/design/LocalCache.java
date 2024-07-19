package design;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. need k,v structure
 * 2. a structure which can hold item & put them in it
 * 3. the item can be higher while it's shot on
 * 4. the item which expired should be discard
 * 5. get a item should be a complex as O(1)
 */
public class LocalCache<K,V> {

    private Map<K, CacheItem<V>> cacheMap;

    private long defaultExpirationTimeMillis;

    public LocalCache(long defaultExpirationTimeMillis) {
        this.cacheMap = new HashMap<>();
        this.defaultExpirationTimeMillis = defaultExpirationTimeMillis;
    }

    public void put(K key, V value) {
        put(key, value, defaultExpirationTimeMillis);
    }

    public void put(K key, V value, long expirationTimeMillis) {
        long expirationTimestamp = System.currentTimeMillis() + expirationTimeMillis;
        cacheMap.put(key, new CacheItem<>(value, expirationTimestamp));
    }

    public V get(K key) {
        CacheItem<V> item = cacheMap.get(key);
        if (item == null || System.currentTimeMillis() > item.getExpirationTimestamp()) {
            cacheMap.remove(key);
            return null;
        }
        return item.getValue();
    }

    public static class CacheItem<V> {
        private V value;
        private long expirationTimestamp;

        public CacheItem(V value, long expirationTimestamp) {
            this.value = value;
            this.expirationTimestamp = expirationTimestamp;
        }

        public V getValue() {
            return value;
        }

        public long getExpirationTimestamp() {
            return expirationTimestamp;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTimestamp;
        }
    }

    public static void main(String[] args) {
        LocalCache<String, String> cache = new LocalCache<>(5000);
        cache.put("key1", "value1");

        System.out.println(cache.get("key1"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cache.get("key1"));
    }
}
