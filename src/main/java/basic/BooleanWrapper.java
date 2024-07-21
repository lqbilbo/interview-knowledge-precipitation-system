package basic;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class BooleanWrapper {
    private int size = 10;
    private Node<Integer> first;
    private Node<Integer> last;
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(BooleanWrapper.class).toPrintable());
        boolean[] value = new boolean[3];
        System.out.println(ClassLayout.parseInstance(value).toPrintable());
        System.out.println(System.getProperty("java.version"));

        ArrayList<Integer> list1 = new ArrayList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        HashMap<Integer, Integer> map1 = new HashMap<>();
        Hashtable<Integer, Integer> map2 = new Hashtable<>();
    }

    private Node<Integer> node(int index) {
        if (index < (size >> 1)) {
            Node<Integer> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<Integer> x = last;
            for (int i = size; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
