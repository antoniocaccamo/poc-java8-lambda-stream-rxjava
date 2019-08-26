package me.antoniocaccamo.sample.concurrency;

import me.antoniocaccamo.sample.concurrency.runnables.MyThread;
import io.micronaut.configuration.picocli.PicocliRunner;

import io.micronaut.context.annotation.Value;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.sql.DataSource;


@Command(name = "command-thread",
        description = "...",
        mixinStandardHelpOptions = true
)
@Slf4j
public class CommandThread implements Runnable {

    @Value("${micronaut.application.name}")
    private String name;

    @Value("${app.file}")
    private String file;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Inject
    DataSource dataSource;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(CommandThread.class, args);
    }

    public void run() {
        log.info("name {} file {}", name, file);
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }



        MyThread myThread0 = new MyThread(file);
        MyThread myThread1 = new MyThread(file);
        MyThread myThread2 = new MyThread(file);

        myThread0.start();
        myThread1.start();
        myThread2.start();
    }
}
