# try-crypto
Project for cryptocurrency try

This is the log output from simple application

<pre>

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::             (v2.0.0.M6)

2017-11-14 09:24:51.131  INFO 13384 --- [           main] c.e.trycrypto.TryCryptoApplication       : Starting TryCryptoApplication on kseniia-ThinkPad-E560 with PID 13384 (/home/kseniia/workspace_crypto/try-crypto/bin started by kseniia in /home/kseniia/workspace_crypto/try-crypto)
2017-11-14 09:24:51.135  INFO 13384 --- [           main] c.e.trycrypto.TryCryptoApplication       : No active profile set, falling back to default profiles: default
2017-11-14 09:24:51.203  INFO 13384 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@647fd8ce: startup date [Tue Nov 14 09:24:51 EET 2017]; root of context hierarchy
2017-11-14 09:24:52.075  INFO 13384 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-11-14 09:24:52.108  INFO 13384 --- [           main] c.e.trycrypto.TryCryptoApplication       : Started TryCryptoApplication in 1.471 seconds (JVM running for 2.167)
2017-11-14 09:24:52.339  INFO 13384 --- [           main] org.bitcoinj.core.Context                : Creating bitcoinj 0.14.5 context.
Started service
2017-11-14 09:24:52.396  INFO 13384 --- [AppKit STARTING] org.bitcoinj.kits.WalletAppKit           : Starting up with directory = /home/kseniia/tmp/bitcoin
2017-11-14 09:24:52.774  INFO 13384 --- [AppKit STARTING] org.bitcoinj.crypto.MnemonicCode         : PBKDF2 took 310,1 ms
2017-11-14 09:24:52.924  INFO 13384 --- [AppKit STARTING] org.bitcoinj.wallet.KeyChainGroup        : Creating and activating a new HD chain: org.bitcoinj.wallet.DeterministicKeyChain@1fcb422e
2017-11-14 09:24:52.949  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : 1 keys needed for M/0H/0 = 1 issued + 0 lookahead size + 0 lookahead threshold - 0 num children
2017-11-14 09:24:52.952  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : Took 2,788 ms
2017-11-14 09:24:53.092  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : 133 keys needed for M/0H/0 = 1 issued + 100 lookahead size + 33 lookahead threshold - 1 num children
2017-11-14 09:24:53.250  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : Took 158,2 ms
2017-11-14 09:24:53.251  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : 133 keys needed for M/0H/1 = 0 issued + 100 lookahead size + 33 lookahead threshold - 0 num children
2017-11-14 09:24:53.310  INFO 13384 --- [AppKit STARTING] o.bitcoinj.wallet.DeterministicKeyChain  : Took 59,30 ms
2017-11-14 09:24:53.326  INFO 13384 --- [AppKit STARTING] org.bitcoinj.store.SPVBlockStore         : Creating new SPV block chain file /home/kseniia/tmp/bitcoin/prfx_.spvchain
2017-11-14 09:24:53.334  INFO 13384 --- [AppKit STARTING] org.bitcoinj.core.CheckpointManager      : Attempting to initialize a new block store with a checkpoint for time 1510039492 (2017-11-07T07:24:52Z)
2017-11-14 09:24:53.393  INFO 13384 --- [AppKit STARTING] org.bitcoinj.core.CheckpointManager      : Read 569 checkpoints, hash is eaf96353fae7cad8f3d53f6ce7916c16678ba9c1595e44977ec3ad505f5804bc
2017-11-14 09:24:53.395  INFO 13384 --- [AppKit STARTING] org.bitcoinj.core.AbstractBlockChain     : chain head is at height 1147104:
 block: 
   hash: 0000000000000a1c34e39a6ff2c396c9c4cede41897c7475b47438ef94a7e6e0
   version: 536870912 (BIP34, BIP66, BIP65)
   previous block: 000000000000343594e671158b6e1b4b6499f6ad66e2aeabf1f6d295d3dba850
   merkle root: af0e27e955aa0905dd7e9b63164cd297b0d0b36a6fb69f2b4fafa81dd613b8d2
   time: 1497962943 (2017-06-20T12:49:03Z)
   difficulty target (nBits): 439714496
   nonce: 2936294172
</pre>
