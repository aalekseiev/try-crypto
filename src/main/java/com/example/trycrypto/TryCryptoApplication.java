package com.example.trycrypto;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletProtobufSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.trycrypto.repository.PersistableWalletRepository;
import com.google.common.util.concurrent.Service;

@SpringBootApplication
public class TryCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryCryptoApplication.class, args);
	}
	
	@Autowired
	private PersistableWalletRepository repo;

	@Bean
	CommandLineRunner cmdrunner() {
		return args -> {
			BriefLogFormatter.init();
			
			NetworkParameters params = new MainNetParams();
			
			MyWalletAppKit wak = new MyWalletAppKit(repo, params);
			Service startAsync = wak.startAsync();
			
			System.out.println("Started service");
			Thread.sleep(10000);
			
			Wallet wallet = wak.wallet();
			System.out.println("!!!!!!!!!!!!!!!!");
//			System.out.println("wallet.params" + wallet.);
			System.out.println("!!!!!!!!!!!!!!!!");
			
			System.out.println("wallet.balance.value=" + wak.wallet().getBalance().getValue());

			System.out.println("Going to stop async");
			Service stopAsync = wak.stopAsync();
			
		};
	}
}
