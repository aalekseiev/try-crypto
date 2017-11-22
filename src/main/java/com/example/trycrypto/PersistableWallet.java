package com.example.trycrypto;

import static org.springframework.data.annotation.AccessType.Type.PROPERTY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import org.bitcoinj.core.Context;
import org.bitcoinj.wallet.Wallet;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.AccessType;

@Entity
@AccessType(PROPERTY)
public class PersistableWallet {

	private static final String SEQUENCE_WALLETS = "sequence_wallets";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_WALLETS)
	@SequenceGenerator(name = SEQUENCE_WALLETS, sequenceName = SEQUENCE_WALLETS)
	private Long id;

	@Column
	private String name;

	@Column
	private LocalDateTime created;

	@Column
	private LocalDateTime updated;

	@Column
	private Integer walletSizeInBytes;

	@Type(type = "org.hibernate.type.BinaryType")
	@Lob
	private byte[] body;

	public PersistableWallet() {

	}

	@PrePersist
	protected void onCreate() {
		created = LocalDateTime.now();
		walletSizeInBytes = body.length;
	}

	@PreUpdate
	protected void onUpdate() {
		updated = LocalDateTime.now();
		walletSizeInBytes = body.length;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public Integer getWalletSizeInBytes() {
		return walletSizeInBytes;
	}

	public Wallet wallet() {
		return new Wallet((Context) null);
	}

}
