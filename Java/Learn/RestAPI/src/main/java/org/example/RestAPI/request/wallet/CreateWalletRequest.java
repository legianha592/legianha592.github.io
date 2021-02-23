package org.example.RestAPI.request.wallet;

import lombok.Data;

@Data
public class CreateWalletRequest {
    private long user_id;
    private String wallet_name;
}
