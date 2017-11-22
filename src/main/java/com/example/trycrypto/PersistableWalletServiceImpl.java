package com.example.trycrypto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.KeyChainGroup;
import org.bitcoinj.wallet.Protos;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletExtension;
import org.bitcoinj.wallet.WalletProtobufSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.trycrypto.repository.PersistableWalletRepository;
import com.google.common.collect.ImmutableList;

@Component
public class PersistableWalletServiceImpl implements PersistableWalletService {

	private final PersistableWalletRepository repo;
	
	protected WalletProtobufSerializer.WalletFactory walletProtobufFactory;

	@Autowired
	public PersistableWalletServiceImpl(PersistableWalletRepository repo) {
		super();
		this.repo = repo;
	}

	/* (non-Javadoc)
	 * @see com.example.trycrypto.PersistableWalletService#walletExists(java.lang.Long)
	 */
	@Override
	public boolean walletExists(Long walletId) {
		return repo.findById(walletId).isPresent();
	}

	/* (non-Javadoc)
	 * @see com.example.trycrypto.PersistableWalletService#findById(java.lang.Long)
	 */
	@Override
	public Optional<PersistableWallet> findById(Long walletId) {
		return repo.findById(walletId);
	}
	
	public Wallet loadWallet(Long walletId, boolean shouldReplayWallet, NetworkParameters params) throws Exception {
        Wallet wallet;
        PersistableWallet wolit = repo.findById(walletId).get();
        InputStream walletStream = new ByteArrayInputStream(wolit.getBody());
        try {
            List<WalletExtension> extensions = provideWalletExtensions();
            WalletExtension[] extArray = extensions.toArray(new WalletExtension[extensions.size()]);
            Protos.Wallet proto = WalletProtobufSerializer.parseToProto(walletStream);
            final WalletProtobufSerializer serializer;
            if (walletProtobufFactory != null)
                serializer = new WalletProtobufSerializer(walletProtobufFactory);
            else
                serializer = new WalletProtobufSerializer();
            wallet = serializer.readWallet(params, extArray, proto);
            if (shouldReplayWallet)
                wallet.reset();
        } finally {
            walletStream.close();
        }
        return wallet;
    }

	/* (non-Javadoc)
	 * @see com.example.trycrypto.PersistableWalletService#save(com.example.trycrypto.PersistableWallet)
	 */
	@Override
	public PersistableWallet save(PersistableWallet persistableWallet) {
		return repo.save(persistableWallet);
	}
	

    public Wallet createWallet(DeterministicSeed restoreFromSeed, NetworkParameters params) {
        KeyChainGroup kcg;
        if (restoreFromSeed != null)
            kcg = new KeyChainGroup(params, restoreFromSeed);
        else
            kcg = new KeyChainGroup(params);
        if (walletProtobufFactory != null) {
            return walletProtobufFactory.create(params, kcg);
        } else {
            return new Wallet(params, kcg);  // default
        }
    }
    
    /**
     * <p>Override this to return wallet extensions if any are necessary.</p>
     *
     * <p>When this is called, chain(), store(), and peerGroup() will return the created objects, however they are not
     * initialized/started.</p>
     */
    public List<WalletExtension> provideWalletExtensions() throws Exception {
        return ImmutableList.of();
    }

}
