package org.example.RestAPI.service;

import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService implements IWalletService{
    @Autowired
    WalletRepository walletRepository;

    public void addWallet(Wallet wallet){
        walletRepository.saveAndFlush(wallet);
    }

    public Optional<Wallet> findById(long id){
        return walletRepository.findById(id);
    }
}
