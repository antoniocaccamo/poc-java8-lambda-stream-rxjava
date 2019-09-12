package me.antoniocaccamo.java8sample.rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;

import java.util.concurrent.TimeUnit;


@Slf4j
@Command(
        name = "rxjava"
)
public class RxJava implements Runnable{

    @Override
    public void run() {

        log.info("01");
        Observable<Integer> source = Observable.just(1,2,3,4,5);

        source.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("done !!!")
        );


        log.info("02");
        Observable<String> ssource =
                Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon");

        Observable<Integer> lengths = ssource.map(String::length);

        lengths.subscribe(System.out::println);


        log.info("map ..");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .map(String::length)
                .subscribe(System.out::println)
        ;

        log.info("count ");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .count()
                .subscribe(System.out::println);

        log.info("filter : ");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .filter( s -> s.length() >5 )
                .subscribe(System.out::println)
        ;

        log.info("distinct  length ..");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .map(String::length)
                .distinct( )
                .subscribe(System.out::println)
        ;

        log.info("distinct  on length ..");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .distinct( String::length)
                .subscribe(System.out::println)
        ;

        log.info("toList ..");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .toList()
                .subscribe(System.out::println)
        ;

        log.info("distinct  length ..");
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .map(String::length)
                .reduce(0,(current,next) -> current + next)
                .subscribe(System.out::println)
        ;

        log.info("flatMap   ..");
        Observable.just("123/52/6345","23421/534","758/2341/74932")
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .subscribe(System.out::println);

        log.info("flatMap   ..");
        Observable.just("123/52/6345","23421/534","758/2341/74932")
                .flatMapSingle(s -> Observable.fromArray(s.split("/"))
                                            .map(Integer::valueOf)
                                            .reduce(0,(curr, next) -> curr + next)
                )
            .subscribe(System.out::println)
        ;


        log.info("subscribeOn   ..");
        Observable sthread = Observable.range(1,5)
                .subscribeOn(Schedulers.newThread());
        sthread.subscribe(i ->
                        log.info("subscriber 1 :  Receiving {} on thread {}", i, Thread.currentThread().getName())
                );
        sthread.subscribe(i ->
                log.info("subscriber 2 :  Receiving {} on thread {}", i, Thread.currentThread().getName())
        )
        ;

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}