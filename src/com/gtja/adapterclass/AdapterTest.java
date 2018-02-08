package com.gtja.adapterclass;

public class AdapterTest {
    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();

        new Wrapper(new Source()).method1();
        new Wrapper(new Source()).method2();
    }
}
