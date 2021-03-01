package org.example.RestAPI.response.record;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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
}
