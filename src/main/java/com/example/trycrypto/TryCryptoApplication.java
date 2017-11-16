package com.example.trycrypto;

import java.io.File;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.utils.BriefLogFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.util.concurrent.Service;

@SpringBootApplication
public class TryCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryCryptoApplication.class, args);
	}

	@Bean
	CommandLineRunner cmdrunner() {
		return args -> {
			BriefLogFormatter.init();
			
			NetworkParameters params = new MainNetParams(); //TestNet3Params();
			WalletAppKit wak = new WalletAppKit(params , new File("/home/kseniia/tmp/bitcoin"), "prfx_");
			Service startAsync = wak.startAsync();
			System.out.println("Started service");
			Thread.sleep(1000);
			
			System.out.println("wallet.balance.value=" + wak.wallet().getBalance().getValue());
			
			
			
			System.out.println("Going to stop async");
			Service stopAsync = wak.stopAsync();
			
		};
	}
}
