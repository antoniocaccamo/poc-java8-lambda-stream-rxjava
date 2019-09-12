package com.linkedin;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        hello("Iron man", "The Hulk", "Thor");

        String[] stringArray = new String[]{"hi", "hola", "bonjour"};
        Observable<String> observable = Observable.fromArray(stringArray);

        List<Integer> integerList = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Observable<Integer> integerObservable = Observable.fromIterable(integerList);

        Observable<String> singleValueObservable = Observable.just("Just One String");

        //Custom Observable
        Observable.create(emitter -> {
            try {
                //Emit values 0 to 10
                IntStream.range(0, 10).boxed().forEach(integer ->
                        System.out.println("integer = " + integer)
                );
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }

        });

        Observable interval = Observable.interval(100, TimeUnit.MILLISECONDS);

        //Consuming observables

        integerObservable.subscribe(intVal -> System.out.print(intVal + " "));
        System.out.println();
        System.out.println("Using static method reference");
        integerObservable.subscribe(Main::printObservable);
    }

    private static <T> void printObservable(T val) {
        System.out.print(val + " - ");
    }

    public static void hello(String... names) {
        Observable.fromArray(names).subscribe(s -> System.out.println("Hello " + s));
    }
}
