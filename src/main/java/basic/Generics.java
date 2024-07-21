package basic;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

public class Generics {

    static class Person {

        protected void doSomething() {
            System.out.println("person do sth");
        }
    }

    static class Woman extends Person {
        @Override
        protected void doSomething() {
            System.out.println("woman do sth");
        }
    }

    static class Man extends Person {
        @Override
        protected void doSomething() {
            System.out.println("man do sth");
        }
    }

    static class Dog {
        protected void doSomething() {
            System.out.println("dog do sth");
        }
    }

    private Class<? extends Person> person;

    public Generics() {
        person = Man.class;
        System.out.println(person.getClassLoader());
    }

    public void doSomething() {
        System.out.println("do sth");
    }

    public static void main(String[] args) {
        Generics generics = new Generics();
        generics.doSomething();
        System.out.println(ClassLayout.parseClass(FinalExample.class).toPrintable());
    }

    static final class FinalExample {
        private final int value = 10;
        private final static int M = 100;
        private ArrayList<Integer> v = new ArrayList<>();
        private final ArrayList<Integer> value2 = new ArrayList<>(v);
        public final void doSomething() {
            value2.set(0, 1);
        }
    }
}
