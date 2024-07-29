package design;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 维护一个FIFO链表，其他委托给包装的cache去做，典型的装饰模式
 */
public class FifoCache implements Cache {

    private final Cache delegate;
    private Deque<Object> keyList;
    private int size;

    public FifoCache(Cache delegate) {
        this.delegate = delegate;
        this.keyList = new LinkedList<>();
        this.size = 1024;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object key, Object value) {
        cycleKeyList(key);
        delegate.putObject(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyList.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void cycleKeyList(Object key) {
        // 增加记录时判断如果记录已超过1024条，会移除链表的第一个元素，从而达到FIFO缓存效果
        keyList.add(key);
        if (delegate.getSize() > size) {
            Object oldestKey = keyList.removeFirst();
            delegate.removeObject(oldestKey);
        }
    }

}
