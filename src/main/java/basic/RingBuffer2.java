package basic;

public class RingBuffer2 {

    private int capacity;
    private int size;
    private int head;
    private int tail;
    private Object[] buffer;

    public RingBuffer2(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        this.buffer = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(Object item) {
        if (isFull()) {
            throw new RuntimeException("Buffer is full");
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
    }

    public Object dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Buffer is empty");
        }
        Object item = buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        size--;
        return item;
    }

    public static void main(String[] args) {
        RingBuffer2 rb = new RingBuffer2(3);
        rb.enqueue(1);
        rb.enqueue(2);
        rb.enqueue(3);

        System.out.println(rb.dequeue());
        rb.enqueue(4);
        rb.enqueue(5);
        rb.enqueue(6);
        System.out.println(rb.dequeue());
    }
}
