package com.example.trycrypto;

import java.util.List;
import java.util.Optional;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletExtension;

public interface PersistableWalletService {

	boolean walletExists(Long walletId);

	Optional<PersistableWallet> findById(Long walletId);

	PersistableWallet save(PersistableWallet persistableWallet);
	
	Wallet loadWallet(Long walletId, boolean shouldReplayWallet, NetworkParameters params) throws Exception;
	
	Wallet createWallet(DeterministicSeed restoreFromSeed, NetworkParameters params);

	List<WalletExtension> provideWalletExtensions() throws Exception;
}