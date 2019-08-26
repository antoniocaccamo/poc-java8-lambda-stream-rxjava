package me.antoniocaccamo.sample.concurrency.runnables;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class MyThread extends Thread{

    private final String file;

    public MyThread(String file) {
        this.file = file;
    }


    @Override
    public void run() {

        InputStream is = getClass().getClassLoader().getResourceAsStream(file);
        if ( is == null) {
            log.error("{} : not found", file);
            return;
        }

        try ( BufferedReader br = new BufferedReader( new InputStreamReader(is)) ) {
            String line = null;
            while ( ( line = br.readLine()) != null){
                log.info("{} - line : {}", Thread.currentThread().getName(), line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
