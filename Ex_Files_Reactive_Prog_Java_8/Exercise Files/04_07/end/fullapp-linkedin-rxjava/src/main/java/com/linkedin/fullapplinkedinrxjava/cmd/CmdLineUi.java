package com.linkedin.fullapplinkedinrxjava.cmd;

import com.linkedin.fullapplinkedinrxjava.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineUi implements CommandLineRunner {

    @Autowired
    private CoinbaseService coinbaseService;

    @Override
    public void run(String... args) throws Exception {

        coinbaseService.getCryptoPrice("BTC-USD")
                .subscribe(s -> System.out.println(s.getData().getAmount()));
    }
}
