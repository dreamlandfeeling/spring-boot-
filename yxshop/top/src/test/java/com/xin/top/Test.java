package com.xin.top;

public class Test {


    class Fruit {
        public String toString() {
            return "Fruit";
        }
    }

    class Apple extends Fruit {
        public String toString() {
            return "Apple";
        }
    }

    class Person {
        public String toString() {
            return "Person";
        }
    }

    class ClassName<T> {
        void show_1(T t) {
            System.out.println("show_1  " + t.toString());
        }

        <E> void show_2(E e) {
            System.out.println("show_2  " + e.toString());
        }

        <T> void show_3(T t) {
            System.out.println("show_3  " + t.toString());
        }


    }
    public static void main(String[] args) {
        Test t = new Test();
        ClassName<Fruit> o = t.new ClassName<Fruit>();
        Fruit f = t.new Fruit();
        Apple a = t.new Apple();
        Person p = t.new Person();
        System.out.println("show_1 演示________________________");
        o.show_1(f);
        o.show_1(a);
//        o.show_1( p );  是不能编译通过的。因为在 ClassName<Fruit>中已经限定了全局的T为Fruit，所以不能再加入Person;
        System.out.println("show_2 演示________________________");
        o.show_2(f);
        o.show_2(a);
        o.<Person>show_2(p);
        System.out.println("show_3 演示________________________");
        o.show_3(f);
        o.show_3(a);
        o.show_3(p);
    }
}
