package by.bsuir.Task_1;

import java.util.*;
import java.util.jar.Manifest;


interface A {
    void x();
}

class B implements A {
    public void x() {}
    public void y() {}
}

class C extends B {
    public void x() {}
}

public class Main  {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        action(s1, s2);
        System.out.print(s1 + " " + s2);
    }
    public static void action(Stack<Integer> x1, Stack<Integer> x2){
        x1.push(Integer.parseInt("10"));
        x2 = x1;
    }
}


