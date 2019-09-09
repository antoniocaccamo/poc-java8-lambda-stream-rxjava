
package me.antoniocaccamo.java8sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionalProgramming {

    private static Evaluate<Integer> evaluate = (list, predicate) -> { 
        for ( Integer i : list ) 
            if ( predicate.test(i) )
                log.info("{}", i);
    };

    
    public static void main(String[] args){

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9);
         
        log.info("evaluating all ..");
        Predicate<Integer> all = n -> true;
        evaluate.evalList(numbers, all);

        log.info("evaluating even ..");
        Predicate<Integer> even = n -> n % 2 == 0 ;
        evaluate.evalList(numbers, even);

        log.info("evaluating greater3 ..");
        Predicate<Integer> greater3 = n -> n > 3 ;
        evaluate.evalList(numbers, greater3);
    }

    interface Evaluate<T> {
        void evalList(List<T> list, Predicate<T> predicate);
    }
    
}


