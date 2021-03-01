package org.example.RestAPI.response.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.RestAPI.model.Wallet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetListRecordResponse {
    @Data
    @AllArgsConstructor
    class MyRecord{
        long id;
        String title;
        String note;
        LocalDateTime created_date;
        LocalDateTime modified_date;
        double amount;
    }

    public GetListRecordResponse(Wallet wallet){
        this.addList_record(wallet);
        this.total_amount = wallet.getTotal_amount();
    }
    private double total_amount;
    private List<MyRecord> list_record = new ArrayList<>();

    private void addList_record(Wallet wallet){

    }
}
