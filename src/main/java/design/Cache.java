package design;

import java.util.concurrent.locks.ReadWriteLock;

public interface Cache {

    private String buildMessage() {
        return "Hello";
    }
    String getId();

    void putObject(Object key, Object value);

    Object getObject(Object key);

    Object removeObject(Object key);

    void clear();

    int getSize();

    default ReadWriteLock getReadWriteLock() {
        return null;
    }
}
