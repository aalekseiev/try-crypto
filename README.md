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

2017-11-14 09:51:58.413  INFO 13883 --- [           main] c.e.trycrypto.TryCryptoApplication       : Starting TryCryptoApplication on kseniia-ThinkPad-E560 with PID 13883 (/home/kseniia/workspace_crypto/try-crypto/bin started by kseniia in /home/kseniia/workspace_crypto/try-crypto)
2017-11-14 09:51:58.417  INFO 13883 --- [           main] c.e.trycrypto.TryCryptoApplication       : No active profile set, falling back to default profiles: default
2017-11-14 09:51:58.480  INFO 13883 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@647fd8ce: startup date [Tue Nov 14 09:51:58 EET 2017]; root of context hierarchy
2017-11-14 09:51:59.316  INFO 13883 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-11-14 09:51:59.336  INFO 13883 --- [           main] c.e.trycrypto.TryCryptoApplication       : Started TryCryptoApplication in 1.464 seconds (JVM running for 2.068)
2017-11-14 09:51:59.534  INFO 13883 --- [           main] org.bitcoinj.core.Context                : Creating bitcoinj 0.14.5 context.
Started service
2017-11-14 09:51:59.574  INFO 13883 --- [AppKit STARTING] org.bitcoinj.kits.WalletAppKit           : Starting up with directory = /home/kseniia/tmp/bitcoin
2017-11-14 09:51:59.810  INFO 13883 --- [AppKit STARTING] org.bitcoinj.core.AbstractBlockChain     : chain head is at height 1230341:
 block: 
   hash: 000000009a200adb932ee185b728b230679ee9d1b3d382129c4a7211f4b7a5f3
   version: 536870912 (BIP34, BIP66, BIP65)
   previous block: 00000000acbe15daa295894945de4c3322169ce8dfec45a7a3d0a80718b5f639
   merkle root: 142ff4cf4fabce05f84010cd6843a20dd291db4bba96a4a148d5e91c6d66d634
   time: 1510644503 (2017-11-14T07:28:23Z)
   difficulty target (nBits): 486604799
   nonce: 3762551672

2017-11-14 09:51:59.888  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Starting ...
2017-11-14 09:51:59.977  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Localhost peer not detected.
wallet.balance.value=0
Going to stop async
2017-11-14 09:52:03.604  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Peer discovery took 3,626 s and returned 135 items
2017-11-14 09:52:03.608  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Waiting 4715 msec before next connect attempt to [93.190.142.127]:18333
2017-11-14 09:52:08.346  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Attempting connection to [34.252.91.57]:18333     (0 connected, 1 pending, 12 max)
2017-11-14 09:52:08.350  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Attempting connection to [2a01:4f8:211:14cf:0:0:0:2]:18333     (0 connected, 2 pending, 12 max)
2017-11-14 09:52:08.352  WARN 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Failed to connect to [2a01:4f8:211:14cf:0:0:0:2]:18333: Сеть недоступна
2017-11-14 09:52:08.353  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : [2a01:4f8:211:14cf:0:0:0:2]:18333: Peer died      (0 connected, 1 pending, 12 max)
2017-11-14 09:52:08.353  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Waiting 1500 msec before next connect attempt to [2001:0:9d38:6ab8:3830:3b9c:aa15:cc84]:18333
2017-11-14 09:52:08.353  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Waiting 1500 msec before next connect attempt to [64.91.249.152]:18333
2017-11-14 09:52:08.547  INFO 13883 --- [ioClientManager] org.bitcoinj.net.NioClientManager        : Connected to testnet-seed.bitcoin.petertodd.org/34.252.91.57:18333
2017-11-14 09:52:08.548  INFO 13883 --- [ioClientManager] org.bitcoinj.core.Peer                   : Announcing to testnet-seed.bitcoin.petertodd.org/34.252.91.57:18333 as: /bitcoinj:0.14.5/
2017-11-14 09:52:08.657  INFO 13883 --- [ioClientManager] org.bitcoinj.core.Peer                   : [34.252.91.57]:18333: Got version=70015, subVer='/Satoshi:0.15.1/', services=0x13, time=2017-11-14 09:52:08, blocks=1230342
2017-11-14 09:52:08.662  INFO 13883 --- [ioClientManager] org.bitcoinj.core.PeerGroup              : [34.252.91.57]:18333: New peer      (1 connected, 0 pending, 12 max)
2017-11-14 09:52:08.667  INFO 13883 --- [ioClientManager] org.bitcoinj.core.PeerGroup              : Setting download peer: [34.252.91.57]:18333
2017-11-14 09:52:08.670  INFO 13883 --- [ioClientManager] o.b.c.listeners.DownloadProgressTracker  : Downloading block chain of size 1. 
2017-11-14 09:52:08.675  INFO 13883 --- [ioClientManager] org.bitcoin.Secp256k1Context             : java.lang.UnsatisfiedLinkError: no secp256k1 in java.library.path
2017-11-14 09:52:09.270  INFO 13883 --- [ioClientManager] o.b.c.listeners.DownloadProgressTracker  : Chain download 100% done with 0 blocks to go, block date 2017-11-14T07:48:26Z
2017-11-14 09:52:09.273  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Stopping ...
2017-11-14 09:52:09.274  INFO 13883 --- [AppKit STOPPING] org.bitcoinj.core.PeerGroup              : Awaiting PeerGroup shutdown ...
2017-11-14 09:52:09.277  INFO 13883 --- [eerGroup Thread] org.bitcoinj.core.PeerGroup              : Stopped.
2017-11-14 09:52:09.921  INFO 13883 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@647fd8ce: startup date [Tue Nov 14 09:51:58 EET 2017]; root of context hierarchy
2017-11-14 09:52:09.923  INFO 13883 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown

</pre>


The dns names are hardcoded inside bitcoinj class MainNetParams.
This is the listing of one of that dns names resolved into ip

<pre>
seed.bitcoin.sipa.be has address 185.21.217.57
seed.bitcoin.sipa.be has address 86.77.91.236
seed.bitcoin.sipa.be has address 188.195.246.19
seed.bitcoin.sipa.be has address 148.251.234.144
seed.bitcoin.sipa.be has address 204.15.192.50
seed.bitcoin.sipa.be has address 45.77.80.116
seed.bitcoin.sipa.be has address 24.88.116.243
seed.bitcoin.sipa.be has address 173.164.81.170
seed.bitcoin.sipa.be has address 90.253.152.123
seed.bitcoin.sipa.be has address 24.107.140.236
seed.bitcoin.sipa.be has address 83.164.131.242
seed.bitcoin.sipa.be has address 83.150.18.225
seed.bitcoin.sipa.be has address 90.178.156.102
seed.bitcoin.sipa.be has address 5.189.165.22
seed.bitcoin.sipa.be has address 108.34.222.147
seed.bitcoin.sipa.be has address 94.226.148.195
seed.bitcoin.sipa.be has address 82.212.7.242
seed.bitcoin.sipa.be has address 192.241.139.18
seed.bitcoin.sipa.be has address 185.4.24.199
seed.bitcoin.sipa.be has address 47.52.190.83
seed.bitcoin.sipa.be has address 85.4.115.66
seed.bitcoin.sipa.be has address 121.214.217.204
seed.bitcoin.sipa.be has address 24.48.172.247
seed.bitcoin.sipa.be has address 97.95.169.43
seed.bitcoin.sipa.be has address 106.15.186.248
seed.bitcoin.sipa.be has IPv6 address 2001:0:4137:9e76:20a0:856:adf2:de90
seed.bitcoin.sipa.be has IPv6 address 2001:0:4137:9e76:97:155f:3e69:27b5
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:6abd:1c06:2a4d:8594:1dfe
seed.bitcoin.sipa.be has IPv6 address 2001:0:4137:9e76:4f6:292f:6294:4c33
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:90d7:38b0:ce83:4902:5cf9
seed.bitcoin.sipa.be has IPv6 address 2001:470:e18f:0:52e5:49ff:fe3f:e74d
seed.bitcoin.sipa.be has IPv6 address 2604:180:1:71f::87d2
seed.bitcoin.sipa.be has IPv6 address 2001:0:4137:9e76:43a:20a9:a780:eef1
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:6ab8:821:eccc:6d61:9a27
seed.bitcoin.sipa.be has IPv6 address 2001:0:4137:9e76:c95:28a7:53b4:a5fa
seed.bitcoin.sipa.be has IPv6 address 2607:5300:120:962::
seed.bitcoin.sipa.be has IPv6 address 2001:0:5ef5:79fb:381c:358b:ba08:b12c
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:6abd:1ce7:533:26f3:2aab
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:78cf:10ee:3cb6:fa96:eb81
seed.bitcoin.sipa.be has IPv6 address 2001:0:9d38:90d7:3027:1b7e:b2eb:96de
</pre>