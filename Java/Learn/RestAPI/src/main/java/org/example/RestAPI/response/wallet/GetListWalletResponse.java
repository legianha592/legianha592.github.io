package org.example.RestAPI.response.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetListWalletResponse {
    @Autowired
    IWalletService walletService;

    @Data
    @AllArgsConstructor
    class MyWallet{
        long id;
        String wallet_name;
        LocalDateTime created_date;
        LocalDateTime modified_date;
        double total_amount;
    }

    public GetListWalletResponse(User user){
        long user_id = user.getId();
        List<Wallet> list = walletService.findByUser_id(user_id);
    }


    private double total_amount;
    private List<MyWallet> list_wallet = new ArrayList<>();
}
