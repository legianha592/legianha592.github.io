package org.example.RestAPI.controller;

import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.request.WalletRequest;
import org.example.RestAPI.service.IUserService;
import org.example.RestAPI.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    IUserService userService;

    @Autowired
    IWalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<Message<Wallet>> createWallet(@RequestBody WalletRequest request){
        Optional<User> user = userService.findById(request.getUser_id());
        Message<Wallet> message;
        if (user.isEmpty()){
            message = new Message<>(FinalMessage.NO_USER, null);
        }
        else{
            Wallet wallet = new Wallet();
            wallet.setWallet_name(request.getWallet_name());
            user.get().addWallet(wallet);
            walletService.addWallet(wallet);

            message = new Message<>(FinalMessage.CREATE_WALLET_SUCCESS, wallet);
        }
        return new ResponseEntity<Message<Wallet>>(message, HttpStatus.OK);
    }
}
