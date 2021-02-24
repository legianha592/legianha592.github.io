package org.example.RestAPI.service;

import org.example.RestAPI.model.Wallet;

import java.util.Optional;

public interface IWalletService {
    void addWallet(Wallet wallet);

    Optional<Wallet> findById(long id);
}
