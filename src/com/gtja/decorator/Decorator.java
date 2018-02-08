package com.gtja.decorator;

public class Decorator implements Source{
    SourceImp sourceImp;

    public Decorator(SourceImp sourceImp) {
        this.sourceImp = sourceImp;
    }

    @Override
    public void method() {
        System.out.println("before");
        sourceImp.method();
        System.out.println("after");
    }
}
