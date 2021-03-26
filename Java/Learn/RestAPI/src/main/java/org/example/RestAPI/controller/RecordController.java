package org.example.RestAPI.controller;

import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.Record;
import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.request.record.CreateRecordRequest;
import org.example.RestAPI.request.record.DeleteRecordRequest;
import org.example.RestAPI.request.record.UpdateRecordRequest;
import org.example.RestAPI.response.record.CreateRecordResponse;
import org.example.RestAPI.response.record.DeleteRecordResponse;
import org.example.RestAPI.response.record.GetListRecordResponse;
import org.example.RestAPI.response.record.UpdateRecordResponse;
import org.example.RestAPI.service.IRecordService;
import org.example.RestAPI.service.ITypeRecordService;
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

    @Autowired
    ITypeRecordService typerecordService;

    @PostMapping("/create")
    public ResponseEntity createRecord(@RequestBody CreateRecordRequest request){
        Optional<Wallet> findWallet = walletService.findById(request.getWallet_id());
        Optional<TypeRecord> findTyperecord = typerecordService.findById(request.getTyperecord_id());
        Message<CreateRecordResponse> message;

        if (findWallet.isEmpty()){
            message = new Message<>(FinalMessage.NO_WALLET, null);
        }
        else if(findTyperecord.isEmpty()){
            message = new Message<>(FinalMessage.NO_TYPERECORD, null);
        }
        else{
            if (!request.getResult().equals("OK")){
                message = new Message<>(request.getResult(), null);
            }
            else{
                //Tạo đối tượng record mới + lấy type record từ db
                Record record = new Record();
                TypeRecord typerecord = findTyperecord.get();
                //setup phía record
                record.setTitle(request.getTitle());
                record.setNote(request.getNote());
                record.setAmount(request.getAmount());
                record.addTyperecord(typerecord);
                //setup phía type record
                typerecord.addRecord(record);
                //add record vào ví
                findWallet.get().addRecord(record);

                //sau khi add record cần cập nhật ngay total amount của ví tại db
                recordService.addRecord(record);
                walletService.updateWallet(request.getWallet_id(), record.getAmount());

                message = new Message<>(FinalMessage.CREATE_RECORD_SUCCESS, new CreateRecordResponse(
                       record.getId(), request.getWallet_id(), request.getTyperecord_id()));
            }
        }
        return new ResponseEntity<Message<CreateRecordResponse>>(message, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateRecord(@RequestBody UpdateRecordRequest request){
        Optional<Record> findRecord = recordService.findById(request.getRecord_id());
        Message<UpdateRecordResponse> message;

        if (findRecord.isEmpty()){
            message = new Message<>(FinalMessage.NO_RECORD, null);
        }
        else{
            if (!request.getResult().equals("OK")){
                message = new Message<>(request.getResult(), null);
            }
            else{
                Record record = findRecord.get();
                Wallet wallet = record.getWallet();
                //sự chênh lệch giữa số cũ và số mới trong bản ghi
                double delta = request.getAmount() - record.getAmount();
                record.setTitle(request.getTitle());
                record.setNote(request.getNote());
                record.setAmount(request.getAmount());

                recordService.addRecord(record);
                walletService.updateWallet(wallet.getId(), delta);

                message = new Message<>(FinalMessage.UPDATE_RECORD_SUCCESS, new UpdateRecordResponse(
                        record.getId(), record.getTitle(), record.getNote(), record.getAmount(), wallet.getId()
                ));
            }
        }
        return new ResponseEntity<Message<UpdateRecordResponse>>(message, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity getListRecord(@RequestParam(name = "walletId") long wallet_id){
        Optional<Wallet> findWallet = walletService.findById(wallet_id);
        Message<GetListRecordResponse> message;

        if (findWallet.isEmpty()){
            message = new Message<>(FinalMessage.NO_WALLET, null);
        }
        else{
            GetListRecordResponse response = new GetListRecordResponse(findWallet.get());
            message = new Message<>(FinalMessage.GET_LIST_RECORD_SUCCESS, response);
        }

        return new ResponseEntity<Message<GetListRecordResponse>>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteRecord(@RequestBody DeleteRecordRequest request){
        Optional<Record> findRecord = recordService.findById(request.getRecord_id());
        Message<DeleteRecordResponse> message;

        if (findRecord.isEmpty()){
            message = new Message<>(FinalMessage.NO_RECORD, null);
        }
        else{
            Record record = findRecord.get();
            Wallet wallet = record.getWallet();

            double delta = record.getAmount();

            recordService.deleteRecord(record);
            walletService.updateWallet(wallet.getId(), -delta);

            message = new Message<>(FinalMessage.DELETE_RECORD_SUCCESS, new DeleteRecordResponse(
                    request.getRecord_id()));
        }

        return new ResponseEntity<Message<DeleteRecordResponse>>(message, HttpStatus.OK);
    }
}
