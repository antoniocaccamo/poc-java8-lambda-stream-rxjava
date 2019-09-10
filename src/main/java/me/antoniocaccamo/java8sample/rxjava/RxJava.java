package me.antoniocaccamo.java8sample.rxjava;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;

@Slf4j
@Command(
    name = "rxjava"
)
public class RxJava implements Runnable{
    
    @Override
    public void run() {

        Observable<Integer> source = Observable.just(1,2,3,4,5);

        source.subscribe(System.out::println);

    }



}