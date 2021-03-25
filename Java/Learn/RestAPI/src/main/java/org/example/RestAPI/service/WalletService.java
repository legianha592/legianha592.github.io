package org.example.RestAPI.service;

import org.example.RestAPI.importer.UserExcelImporter;
import org.example.RestAPI.importer.WalletExcelImporter;
import org.example.RestAPI.model.User;
import org.example.RestAPI.response.exporter.WalletExcelExporterResponse;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    //Tăng ví có id là wallet_id lên một lượng là amount
    public void updateWallet(long wallet_id, double amount) {
        Optional<Wallet> findWallet = walletRepository.findById(wallet_id);
        if (findWallet.isPresent()){
            Wallet wallet = findWallet.get();
            wallet.setTotal_amount(wallet.getTotal_amount() + amount);

            walletRepository.saveAndFlush(wallet);
        }
    }

    @Override
    public void deleteWallet(long wallet_id) {
        Optional<Wallet> findWallet = walletRepository.findById(wallet_id);
        if (findWallet.isPresent()){
            walletRepository.delete(findWallet.get());
        }
    }


    @Override
    public ByteArrayInputStream load() {
        List<Wallet> listWallet = walletRepository.findAll();

        ByteArrayInputStream in = WalletExcelExporterResponse.WalletEntityToExcel(listWallet);
        return in;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            WalletExcelImporter importer = new WalletExcelImporter();
            List<Wallet> listWallet = importer.ExcelToWalletEntity(file.getInputStream());
            walletRepository.saveAll(listWallet);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
