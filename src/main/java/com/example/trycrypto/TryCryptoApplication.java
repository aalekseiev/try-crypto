package com.example.trycrypto;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.utils.BriefLogFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TryCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryCryptoApplication.class, args);
	}

	@Bean
	CommandLineRunner cmdrunner() {
		return args -> {
			BriefLogFormatter.init();
			if (args.length < 2) {
			    System.err.println("Usage: address-to-send-back-to [regtest|testnet]");
			    return;
			}
			
			// Figure out which network we should connect to. Each one gets its own set of files.
			NetworkParameters params;
			String filePrefix;
			if (args[1].equals("testnet")) {
			    params = TestNet3Params.get();
			    filePrefix = "forwarding-service-testnet";
			} else if (args[1].equals("regtest")) {
			    params = RegTestParams.get();
			    filePrefix = "forwarding-service-regtest";
			} else {
			    params = MainNetParams.get();
			    filePrefix = "forwarding-service";
			}
		};
	}
}
