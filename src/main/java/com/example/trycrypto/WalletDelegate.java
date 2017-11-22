package com.example.trycrypto;

import java.io.IOException;
import java.io.OutputStream;

public class WalletDelegate extends OutputStream {

	public PersistableWallet pw;

	public WalletDelegate(PersistableWallet pw) {
		super();
		this.pw = pw;
	}

	public boolean exists() {
		return pw != null;
	}

	@Override
	public void write(int b) throws IOException {

	}

}
