package design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LocalCacheWithTimeout<K, V> {
    private ConcurrentHashMap<K, LocalCache.CacheItem<V>> cacheMap;
    private ScheduledExecutorService executorService;

    public LocalCacheWithTimeout() {
        cacheMap = new ConcurrentHashMap<>();
        executorService = new ScheduledThreadPoolExecutor(1);
        executorService.scheduleAtFixedRate(() -> {
            for (K key : cacheMap.keySet()) {
                LocalCache.CacheItem<V> element = cacheMap.get(key);
                if (element.isExpired()) {
                    cacheMap.remove(key);
                }
            }
        },0,1, TimeUnit.MINUTES);
    }

    public void put(K key, V value, long timeout, TimeUnit unit) {
        long expirationTime = System.currentTimeMillis() + unit.toMillis(timeout);
        cacheMap.put(key, new LocalCache.CacheItem<>(value, expirationTime));
    }

    public V get(K key) {
        LocalCache.CacheItem<V> element = cacheMap.get(key);
        if (element != null && !element.isExpired()) {
            return element.getValue();
        }
        return null;
    }

    public static void main(String[] args) {
        LocalCacheWithTimeout<String, String> cache = new LocalCacheWithTimeout<>();
        cache.put("key1", "value1", 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = cache.get("key1");
        if (value != null) {
            System.out.println("Value: " + value);
        } else {
            System.out.println("Cahe expired or not found");
        }
    }
}
