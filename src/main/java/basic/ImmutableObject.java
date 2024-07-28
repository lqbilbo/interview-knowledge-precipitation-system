package basic;

import java.io.IOException;
import java.util.*;

public final class ImmutableObject {

    private final int a;
    private final HashMap<Integer, Integer> b;
    public ImmutableObject() {
        a = 100;
        b = new HashMap<>();
    }

    public void build() {
//        var list = new ArrayList<String>(); // var是保留字
//        var stream = list.stream();
        List.of();
        List.of("Hello", "World");
        List.of(1,2,3);
        Set.of();
        Set.of("Hello", "World");
        Set.of(1,2,3);
        Map.of();
        Map.of("Hello", 1, "World", 2);
    }

    public int getA() {
        return a;
    }
    public HashMap<Integer, Integer> getB() {
        return b;
    }

    public void processBuilderDemo() throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder("top");
        final ProcessHandle processHandle = processBuilder.start().toHandle();
        processHandle.onExit().whenCompleteAsync((handle, throwable) -> {
            if (throwable == null) {
                System.out.println(handle.pid());
            } else {
                throwable.printStackTrace();
            }
        });
    }
}
