package org.example.RestAPI.service;

import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService implements IWalletService{
    @Autowired
    WalletRepository walletRepository;

    @Override
    public void addWallet(Wallet wallet){
        walletRepository.saveAndFlush(wallet);
    }

    @Override
    public Optional<Wallet> findById(long id){
        return walletRepository.findById(id);
    }

    @Override
    public List<Wallet> findByUser_id(long id) {
        return walletRepository.findByUser_id(id);
    }

    @Override
    public void updateWallet(User user) {
        
    }
}
