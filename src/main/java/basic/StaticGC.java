package basic;

public class StaticGC {

    static int[] ia = new int[1024 * 1024];

    public static void main(String[] args) {
        int i = 0;
        do {
            if (3 == i++) {
                ia = null;
                System.out.println("release");
            }
            System.gc();
        } while (i < 6);
    }
}
