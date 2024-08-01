package basic;

public class RingBuffer {
    // 最大容量
    int capacity;
    // 元素数组
    char[] elements;
    // 当前插入元素个数
    int size;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.elements = new char[capacity];
        this.size = 0;
    }

    public void pushItem(char ele) {
        if (isFull()) {
            for (int i = 0; i < capacity-1; i++) {
                elements[i] = elements[i+1];
            }
            elements[capacity-1] = ele;
            return;
        }
        elements[size] = ele;
        size++;
    }

    public char pullItem() {
        if (isEmpty()) {
            throw new RuntimeException("buffer is empty");
        }
        char ele = elements[capacity - size];
        elements[capacity - size] = 0;
        size--;
        return ele;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer(3);
        ringBuffer.pushItem('1');
        ringBuffer.pushItem('2');
        System.out.println(ringBuffer.pullItem());
        ringBuffer.pushItem('3');
        ringBuffer.pushItem('4');
        ringBuffer.pushItem('5');
        ringBuffer.pushItem('6');
        System.out.println(ringBuffer.pullItem());
        System.out.println(ringBuffer.pullItem());
        System.out.println(ringBuffer.pullItem());
        System.out.println(ringBuffer.pullItem());
    }
}
