package org.example.RestAPI.controller;

import org.apache.poi.ss.formula.functions.T;
import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.request.typerecord.CreateTypeRecordRequest;
import org.example.RestAPI.response.typerecord.CreateTypeRecordResponse;
import org.example.RestAPI.service.ITypeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/typeRecord")
public class TypeRecordController {
    @Autowired
    ITypeRecordService typeRecordService;

    @PostMapping("/create")
    public ResponseEntity createTypeRecord(@RequestBody CreateTypeRecordRequest request){
        Optional<TypeRecord> findTypeRecord = typeRecordService.findByTypeRecord_name(request.getTypeRecord_name());
        Message<CreateTypeRecordResponse> message;

        if (findTypeRecord.isEmpty()){
            if (request.getResult().equals("OK")){
                TypeRecord typerecord = new TypeRecord();
                typerecord.setTypeRecord_name(request.getTypeRecord_name());
                typerecord.setImage_url(request.getImage_url());

                typeRecordService.addTypeRecord(typerecord);

                message = new Message<>(FinalMessage.CREATE_TYPERECORD_SUCCESS, new CreateTypeRecordResponse(
                        typerecord.getId()));
            }
            else{
                message = new Message<>(request.getResult(), null);
            }
        }
        else{
            message = new Message<>(FinalMessage.TYPERECORD_EXISTED, null);
        }
        return new ResponseEntity<Message<CreateTypeRecordResponse>>(message, HttpStatus.OK);
    }

}
