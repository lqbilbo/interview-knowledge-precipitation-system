package design;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 最近最少使用缓存
 * 基于 LinkedHashMap覆盖 removeEldestEntry 方法实现
 */
public class LruCache implements Cache {

    private final Cache delegate;
    // 额外用了一个map才能做lru，但是委托的Cache里面其实也是一个map，相当于用2倍的内存实现lru功能
    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public LruCache(Cache delegate) {
        this.delegate = delegate;
    }
    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        // get的时候调用一下LinkedHashMap
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }



    @Override
    public ReadWriteLock getReadWriteLock() {
        return Cache.super.getReadWriteLock();
    }
}
