package me.antoniocaccamo.java8sample;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import me.antoniocaccamo.java8sample.functionalprogramming.FunctionalProgramming;
import me.antoniocaccamo.java8sample.lambda.LambdaExpression;
import me.antoniocaccamo.java8sample.rxjava.RxJava;
import me.antoniocaccamo.java8sample.rxjava.rxjavafx.*;
import picocli.CommandLine.Command;


@Command(name = "java8sample",
        version = {"1.0.0"},
        description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {
                LambdaExpression.class,
                FunctionalProgramming.class,
                RxJava.class,
                RxJavaFxA.class,
                RxJavaFxB.class,
                RxJavaFxC.class,
                PlayerRxJavaFx.class
        }
)
@Slf4j
public class Java8sample implements Runnable {

    @Value("${micronaut.application.name}")
    private String name;

    //@Value("${app.file}")
    //private String file;

    //@Option(names = {"-v", "--verbose"}, description = "...")
    //boolean verbose;

    //@Inject
    //DataSource dataSource;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(Java8sample.class, args);
    }

    public void run() {
        picocli.CommandLine.usage(this, System.out);
    }
}
