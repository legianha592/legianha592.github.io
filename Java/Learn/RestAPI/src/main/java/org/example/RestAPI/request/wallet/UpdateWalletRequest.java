package org.example.RestAPI.request.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWalletRequest {
    private long wallet_id;
    private String wallet_name;
}
