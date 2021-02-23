package org.example.RestAPI.request.wallet;

import lombok.Data;

@Data
public class WalletRequest {
    private Long user_id;
    private String wallet_name;
}
