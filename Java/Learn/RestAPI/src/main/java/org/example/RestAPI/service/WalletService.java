package org.example.RestAPI.service;

import org.example.RestAPI.importer.UserExcelImporter;
import org.example.RestAPI.importer.WalletExcelImporter;
import org.example.RestAPI.model.Record;
import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.model.User;
import org.example.RestAPI.repository.TypeRecordRepository;
import org.example.RestAPI.response.exporter.WalletExcelExporterResponse;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class WalletService implements IWalletService{
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TypeRecordRepository typeRecordRepository;

    @Autowired
    IUserService userService;

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
    public void updateTypeRecordConnection(long wallet_id, TypeRecord old_type, TypeRecord new_type) {
        if (old_type.getId() == new_type.getId()){
            return;
        }

        Optional<Wallet> findWallet = walletRepository.findById(wallet_id);
        //Duyet qua record cua vi, neu co >1 record cung type thi khong xoa lien ket cu, con neu == 1 thi phai xoa lien ket cu
        if (findWallet.isPresent()){
            Wallet wallet = findWallet.get();
            int count = 0;
            for (Record record : wallet.getListRecord()){
                if (record.getTypeRecord().getId() == old_type.getId()){
                    count++;
                }
                if (count > 1){
                    return;
                }
            }
            //Xoa lien he cu
            wallet.deleteTypeRecord(old_type);
            old_type.deleteWallet(wallet);
            //Them lien he moi
            wallet.addTypeRecord(new_type);
            new_type.addWallet(wallet);

            walletRepository.saveAndFlush(wallet);
            typeRecordRepository.saveAndFlush(old_type);
            typeRecordRepository.saveAndFlush(new_type);
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
            HashMap<Wallet, Long> mapWallet = WalletExcelImporter.ExcelToWalletEntity(file.getInputStream());
            List<Wallet> listWallet = new ArrayList<>();
            for (Map.Entry<Wallet, Long> entry : mapWallet.entrySet()){
                Optional<User> user = userService.findById(entry.getValue());
                if(user.isPresent()){
                    entry.getKey().setUser(user.get());
                }
                else{
                    entry.getKey().setUser(null);
                }
                listWallet.add(entry.getKey());
            }
            walletRepository.saveAll(listWallet);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
