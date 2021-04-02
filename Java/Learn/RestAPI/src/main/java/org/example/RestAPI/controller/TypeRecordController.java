package org.example.RestAPI.controller;

import org.apache.poi.ss.formula.functions.T;
import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.request.typerecord.CreateTypeRecordRequest;
import org.example.RestAPI.request.typerecord.UpdateTypeRecordRequest;
import org.example.RestAPI.response.typerecord.CreateTypeRecordResponse;
import org.example.RestAPI.response.typerecord.UpdateTypeRecordResponse;
import org.example.RestAPI.service.ITypeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
                TypeRecord typeRecord = new TypeRecord();
                typeRecord.setTypeRecord_name(request.getTypeRecord_name());
                typeRecord.setImage_url(request.getImage_url());

                typeRecordService.addTypeRecord(typeRecord);

                message = new Message<>(FinalMessage.CREATE_TYPERECORD_SUCCESS, new CreateTypeRecordResponse(
                        typeRecord.getId()));
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

    @PutMapping("/update")
    public ResponseEntity updateTypeRecord(@RequestBody UpdateTypeRecordRequest request){
        Optional<TypeRecord> findTypeRecord = typeRecordService.findById(request.getTypeRecord_id());
        Message<UpdateTypeRecordResponse> message;

        if (findTypeRecord.isEmpty()){
            message = new Message<>(FinalMessage.NO_TYPERECORD, null);
        }
        else{
            if (request.getResult().equals("OK")){
                TypeRecord typeRecord = findTypeRecord.get();
                typeRecord.setTypeRecord_name(request.getTypeRecord_name());
                typeRecord.setImage_url(request.getImage_url());

                typeRecordService.addTypeRecord(typeRecord);

                message = new Message<>(FinalMessage.UPDATE_TYPERECORD_SUCCESS, new UpdateTypeRecordResponse(
                        typeRecord.getId(), typeRecord.getTypeRecord_name(), typeRecord.getImage_url()
                ));
            }
            else{
                message = new Message<>(request.getResult(), null);
            }
        }
        return new ResponseEntity<Message<UpdateTypeRecordResponse>>(message, HttpStatus.OK);
    }




    @GetMapping("/export/excel")
    public ResponseEntity<Resource> getFile(){
        String filename = "typeRecord.xlsx";
        InputStreamResource file = new InputStreamResource(typeRecordService.loadTypeRecord());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
