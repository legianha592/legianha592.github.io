package org.example.RestAPI.response.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class GetListTypeRecordOfWalletResponse {
    @Data
    @AllArgsConstructor
    static
    class MyTypeRecord{
        long id;
        private String typeRecord_name;
        private String image_url;
    }

    public GetListTypeRecordOfWalletResponse(Wallet wallet){
        Set<TypeRecord> set = wallet.getSetTypeRecord();
        List<TypeRecord> list =  new ArrayList<>(set);
        for (TypeRecord typeRecord : list) {
            MyTypeRecord myTypeRecord = new MyTypeRecord(typeRecord.getId(),typeRecord.getTypeRecord_name(),typeRecord.getImage_url());
            list_typeRecord.add(myTypeRecord);
        }

    }

    private List<MyTypeRecord> list_typeRecord = new ArrayList<>();

}
