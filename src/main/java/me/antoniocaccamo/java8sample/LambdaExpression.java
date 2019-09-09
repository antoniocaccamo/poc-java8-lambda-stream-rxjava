package me.antoniocaccamo.java8sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LambdaExpression {

    private static final Logger log = LoggerFactory.getLogger(LambdaExpression.class);

    // with type definition
    private static MathOperation addiction = (int a,int b) -> a + b;

    // without type definition
    private static MathOperation substruction = (int a,int b) -> a - b;

    // without type definition
    private static MathOperation multiplication = ( a, b) -> {return (a * b);};

    // without type definition
    private static MathOperation division = ( a, b) -> a / b;

    private static GreatingService systemOutGreatingService =
        (message) -> System.out.format("ciaooooo %s\n", message);

        private static GreatingService logGreatingService =
        (message) -> log.info("log-ciaooooo {}", message);

    public static void main(String[] args){

        int a = 10, b = 2;
        LambdaExpression.operate(a, b, addiction);   
        LambdaExpression.operate(a, b, substruction);
        LambdaExpression.operate(a, b, multiplication);
        LambdaExpression.operate(a, b, division);

        systemOutGreatingService.ssaayyMMeessssaaggee(LambdaExpression.class.getSimpleName());
        logGreatingService.ssaayyMMeessssaaggee(LambdaExpression.class.getSimpleName());
    }

    private static void  operate(int a, int b, MathOperation operation) {
        log.info("{} {} {} => {}", operation, a, b, operation.doOperation(a, b) );
    }


    interface MathOperation {
        int doOperation(int a, int b);
    }
    
    interface GreatingService {
        void ssaayyMMeessssaaggee(String message);
        }

    
}

