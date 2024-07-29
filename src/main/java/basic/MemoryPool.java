package basic;

public class MemoryPool {
    // 内存池的大小
    private int size;
    // 内存池的空闲空间
    private byte[] memory;

    // 构造函数，初始化内存池的大小和空间
    public MemoryPool(int size) {
        this.size = size;
        this.memory = new byte[size];
    }

    // 分配内存
    public int allocate(int size) {
        int start = 0;
        int end = 0;
        boolean found = false;

        // 遍历内存池，查找连续空间
        for (int i = 0; i < this.size; i++) {
            if (memory[i] == 0) {
                if (!found) {
                    start = i;
                }
                end = i;
                if (end - start + 1 >= size) {
                    found = true;
                    break;
                }
            } else {
                found = false;
            }
        }

        if (found) {
            // 标记分配的内存
            for (int i = start; i < end; i++) {
                memory[i] = 1;
            }
            return start;
        } else {
            return -1;
        }
    }

    // 回收内存
    public void garbageCollect() {
        // 标记所有活跃的对象
        for (int i = 0; i < this.size; i++) {
            if (memory[i] == 1) {
                memory[i] = 2;  // 标记为活跃
            }
        }

        // 清除未被标记的对象
        for (int i = 0; i < this.size; i++) {
            if (memory[i] != 2) {
                memory[i] = 0;  // 清空内存
            }
        }
    }
}

enum MemoryRegion {
    HEAP,
    STACK,
    META
}
