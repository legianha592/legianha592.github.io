package org.example.RestAPI.service;

import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

public interface IWalletService {
    void addWallet(Wallet wallet);

    void updateWallet(long wallet_id, double amount);

    void deleteWallet(long wallet_id);

    Optional<Wallet> findById(long id);

    List<Wallet> findByUser_id(long id);

    ByteArrayInputStream load();
}
