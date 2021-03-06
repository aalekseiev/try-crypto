package com.example.trycrypto;

import static com.google.common.base.Preconditions.checkState;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import javax.annotation.Nullable;

import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.CheckpointManager;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.net.discovery.PeerDiscovery;
import org.bitcoinj.protocols.channels.StoredPaymentChannelClientStates;
import org.bitcoinj.protocols.channels.StoredPaymentChannelServerStates;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class MyWalletAppKit extends AbstractIdleService {
	
	protected static final Logger log = LoggerFactory.getLogger(MyWalletAppKit.class);

	private Wallet vWallet;
	protected volatile PeerGroup vPeerGroup;

	protected volatile Context context;
	
	protected boolean autoStop = true;
	
    protected volatile BlockChain vChain;
    protected volatile BlockStore vStore;
    
    protected boolean useAutoSave = true;
    
    protected boolean blockingStartup = true;
    
    protected String userAgent, version;
    
    @Nullable protected DeterministicSeed restoreFromSeed;

    protected InputStream checkpoints;
    
    protected PeerAddress[] peerAddresses;
    protected DownloadProgressTracker downloadListener;
    
    @Nullable protected PeerDiscovery discovery;

    protected boolean useTor = false;   // Perhaps in future we can change this to true.

    //
    
	private final PersistableWalletService service;

	private final Long walletId;
	
	private final NetworkParameters params;
	
	    
	public MyWalletAppKit(PersistableWalletService service, NetworkParameters params, Long walletId) {
		super();
		this.service = service;
		this.params = params;
		this.context = new Context(params);
		this.walletId = walletId;
	}
	
	/**
     * Override this to use a {@link BlockStore} that isn't the default of {@link SPVBlockStore}.
     */
    protected BlockStore provideBlockStore(File file) throws BlockStoreException {
        return new SPVBlockStore(params, file);
    }
    
    private Wallet createOrLoadWallet(Long walletId, boolean shouldReplayWallet) throws Exception {
        Wallet wallet;

        maybeMoveOldWalletOutOfTheWay();

        Optional<PersistableWallet> pw = service.findById(walletId);
                
        if (pw.isPresent()) {
            wallet = service.loadWallet(walletId, shouldReplayWallet, params);
        } else {
            wallet = service.createWallet(restoreFromSeed, params);
            wallet.freshReceiveKey();
            for (WalletExtension e : service.provideWalletExtensions()) {
                wallet.addExtension(e);
            }

            // Currently the only way we can be sure that an extension is aware of its containing wallet is by
            // deserializing the extension (see WalletExtension#deserializeWalletExtension(Wallet, byte[]))
            // Hence, we first save and then load wallet to ensure any extensions are correctly initialized.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wallet.saveToFileStream(baos);
            
            PersistableWallet persistableWallet = new PersistableWallet();
            persistableWallet.setBody(baos.toByteArray());
            persistableWallet.setName("LalalaTratata wolit");
            persistableWallet = service.save(persistableWallet);
            
            wallet = service.loadWallet(persistableWallet.getId(), false, params);
        }

//        if (useAutoSave) {
//            this.setupAutoSave(wallet);
//        }

        return wallet;
    }
    
    private void maybeMoveOldWalletOutOfTheWay() {
    	log.warn("commented out this method. Maybe it is not needed in case of db wallet storage");
//        if (restoreFromSeed == null) return;
//        if (!vWalletFile.exists()) return;
//        int counter = 1;
//        File newName;
//        do {
//            newName = new File(vWalletFile.getParent(), "Backup " + counter + " for " + vWalletFile.getName());
//            counter++;
//        } while (newName.exists());
//        log.info("Renaming old wallet file {} to {}", vWalletFile, newName);
//        if (!vWalletFile.renameTo(newName)) {
//            // This should not happen unless something is really messed up.
//            throw new RuntimeException("Failed to rename wallet for restore");
//        }
    }
    

    
//    protected void setupAutoSave(Wallet wallet) {
//        wallet.autosaveToFile(vWalletFile, 5, TimeUnit.SECONDS, null);
//    }

    protected PeerGroup createPeerGroup() throws TimeoutException {
            return new PeerGroup(params, vChain);
    }

	@Override
    protected void startUp() throws Exception {
        // Runs in a separate thread.
        Context.propagate(context);
//        if (!directory.exists()) {
//            if (!directory.mkdirs()) {
//                throw new IOException("Could not create directory " + directory.getAbsolutePath());
//            }
//        }
//        log.info("Starting up with directory = {}", directory);
        try {
            File chainFile = new File("my.spvchain");
            boolean chainFileExists = chainFile.exists();
            
            boolean walletFileExists = service.walletExists(walletId);
            
            boolean shouldReplayWallet = (walletFileExists && !chainFileExists) || restoreFromSeed != null;
            vWallet = createOrLoadWallet(walletId, shouldReplayWallet);

            // Initiate Bitcoin network objects (block store, blockchain and peer group)
            vStore = provideBlockStore(chainFile);
            if (!chainFileExists || restoreFromSeed != null) {
                if (checkpoints == null && !Utils.isAndroidRuntime()) {
                    checkpoints = CheckpointManager.openStream(params);
                }

                if (checkpoints != null) {
                    // Initialize the chain file with a checkpoint to speed up first-run sync.
                    long time;
                    if (restoreFromSeed != null) {
                        time = restoreFromSeed.getCreationTimeSeconds();
                        if (chainFileExists) {
                            log.info("Deleting the chain file in preparation from restore.");
                            vStore.close();
                            if (!chainFile.delete())
                                throw new IOException("Failed to delete chain file in preparation for restore.");
                            vStore = new SPVBlockStore(params, chainFile);
                        }
                    } else {
                        time = vWallet.getEarliestKeyCreationTime();
                    }
                    if (time > 0)
                        CheckpointManager.checkpoint(params, checkpoints, vStore, time);
                    else
                        log.warn("Creating a new uncheckpointed block store due to a wallet with a creation time of zero: this will result in a very slow chain sync");
                } else if (chainFileExists) {
                    log.info("Deleting the chain file in preparation from restore.");
                    vStore.close();
                    if (!chainFile.delete())
                        throw new IOException("Failed to delete chain file in preparation for restore.");
                    vStore = new SPVBlockStore(params, chainFile);
                }
            }
            vChain = new BlockChain(params, vStore);
            vPeerGroup = createPeerGroup();
            if (this.userAgent != null)
                vPeerGroup.setUserAgent(userAgent, version);

            // Set up peer addresses or discovery first, so if wallet extensions try to broadcast a transaction
            // before we're actually connected the broadcast waits for an appropriate number of connections.
            if (peerAddresses != null) {
                for (PeerAddress addr : peerAddresses) vPeerGroup.addAddress(addr);
                vPeerGroup.setMaxConnections(peerAddresses.length);
                peerAddresses = null;
            } else if (!params.getId().equals(NetworkParameters.ID_REGTEST) && !useTor) {
                vPeerGroup.addPeerDiscovery(discovery != null ? discovery : new DnsDiscovery(params));
            }
            vChain.addWallet(vWallet);
            vPeerGroup.addWallet(vWallet);
            onSetupCompleted();

            if (blockingStartup) {
                vPeerGroup.start();
                // Make sure we shut down cleanly.
                installShutdownHook();
                completeExtensionInitiations(vPeerGroup);

                // TODO: Be able to use the provided download listener when doing a blocking startup.
                final DownloadProgressTracker listener = new DownloadProgressTracker();
                vPeerGroup.startBlockChainDownload(listener);
                listener.await();
            } else {
                Futures.addCallback(vPeerGroup.startAsync(), new FutureCallback() {
                    @Override
                    public void onSuccess(@Nullable Object result) {
                        completeExtensionInitiations(vPeerGroup);
                        final DownloadProgressTracker l = downloadListener == null ? new DownloadProgressTracker() : downloadListener;
                        vPeerGroup.startBlockChainDownload(l);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);

                    }
                });
            }
        } catch (BlockStoreException e) {
            throw new IOException(e);
        }
    }
	
	private void installShutdownHook() {
        if (autoStop) Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override public void run() {
                try {
                    MyWalletAppKit.this.stopAsync();
                    MyWalletAppKit.this.awaitTerminated();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

	@Override
    protected void shutDown() throws Exception {
        // Runs in a separate thread.
        try {
            Context.propagate(context);
            vPeerGroup.stop();
            //TODO: replace saving to file with saving to database
//            vWallet.saveToFile(vWalletFile);
            vStore.close();

            vPeerGroup = null;
            vWallet = null;
            vStore = null;
            vChain = null;
        } catch (BlockStoreException e) {
            throw new IOException(e);
        }
    }
	
	/**
     * This method is invoked on a background thread after all objects are initialised, but before the peer group
     * or block chain download is started. You can tweak the objects configuration here.
     */
    protected void onSetupCompleted() { }
	
	public BlockChain chain() {
        checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return vChain;
    }

    public BlockStore store() {
        checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return vStore;
    }

    public Wallet wallet() {
        checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return vWallet;
    }

    public PeerGroup peerGroup() {
        checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return vPeerGroup;
    }

    /*
     * As soon as the transaction broadcaster han been created we will pass it to the
     * payment channel extensions
     */
    private void completeExtensionInitiations(TransactionBroadcaster transactionBroadcaster) {
        StoredPaymentChannelClientStates clientStoredChannels = (StoredPaymentChannelClientStates)
                vWallet.getExtensions().get(StoredPaymentChannelClientStates.class.getName());
        if(clientStoredChannels != null) {
            clientStoredChannels.setTransactionBroadcaster(transactionBroadcaster);
        }
        StoredPaymentChannelServerStates serverStoredChannels = (StoredPaymentChannelServerStates)
                vWallet.getExtensions().get(StoredPaymentChannelServerStates.class.getName());
        if(serverStoredChannels != null) {
            serverStoredChannels.setTransactionBroadcaster(transactionBroadcaster);
        }
    }

}
