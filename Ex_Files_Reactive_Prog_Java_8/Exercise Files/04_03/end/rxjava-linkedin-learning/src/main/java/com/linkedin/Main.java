package com.linkedin;


import io.reactivex.Observable;

public class Main {

    public static void main(String[] args) {
        hello("Iron man", "The Hulk", "Thor");
    }

    public static void hello(String... names) {
        Observable.fromArray(names).subscribe(s -> System.out.println("Hello " + s));
    }
}
