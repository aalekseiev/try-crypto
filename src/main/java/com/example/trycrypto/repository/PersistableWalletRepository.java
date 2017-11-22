package com.example.trycrypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.trycrypto.PersistableWallet;

public interface PersistableWalletRepository
		extends CrudRepository<PersistableWallet, Long>, JpaRepository<PersistableWallet, Long> {

}
