package org.example.RestAPI.controller;

import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.request.wallet.CreateWalletRequest;
import org.example.RestAPI.response.wallet.CreateWalletResponse;
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
    public ResponseEntity createWallet(@RequestBody CreateWalletRequest request){
        Optional<User> findUser = userService.findById(request.getUser_id());
        Message<CreateWalletResponse> message;
        if (findUser.isEmpty()){
            message = new Message<>(FinalMessage.NO_USER, null);
        }
        else{
            if (!request.getResult().equals("OK")){
                message = new Message<>(request.getResult(), null);
            }
            else{
                Wallet wallet = new Wallet();
                wallet.setWallet_name(request.getWallet_name());
                findUser.get().addWallet(wallet);
                walletService.addWallet(wallet);

                message = new Message<>(FinalMessage.CREATE_WALLET_SUCCESS,
                        new CreateWalletResponse(wallet.getId()));
            }
        }
        return new ResponseEntity<Message<CreateWalletResponse>>(message, HttpStatus.OK);
    }
}
