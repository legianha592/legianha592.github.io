package org.example.RestAPI.controller;

import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.Record;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.request.record.CreateRecordRequest;
import org.example.RestAPI.response.record.CreateRecordResponse;
import org.example.RestAPI.service.IRecordService;
import org.example.RestAPI.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    IWalletService walletService;

    @Autowired
    IRecordService recordService;

    @PostMapping("/create")
    public ResponseEntity createRecord(@RequestBody CreateRecordRequest request){
        Optional<Wallet> findWallet = walletService.findById(request.getWallet_id());
        Message<CreateRecordResponse> message;

        if (findWallet.isEmpty()){
            message = new Message<>(FinalMessage.NO_WALLET, null);
        }
        else{
            if (!request.getResult().equals("OK")){
                message = new Message<>(request.getResult(), null);
            }
            else{
                Record record = new Record();
                record.setTitle(request.getTitle());
                record.setNote(request.getNote());
                record.setAmount(request.getAmount());

                findWallet.get().addRecord(record);

                //sau khi add record cần cập nhật ngay total amount của ví tại db
                recordService.addRecord(record);
                walletService.updateWallet(request.getWallet_id(), record.getAmount());

                message = new Message<>(FinalMessage.CREATE_RECORD_SUCCESS, new CreateRecordResponse(
                       record.getId(), request.getWallet_id()));
            }
        }
        return new ResponseEntity<Message<CreateRecordResponse>>(message, HttpStatus.OK);
    }
}
