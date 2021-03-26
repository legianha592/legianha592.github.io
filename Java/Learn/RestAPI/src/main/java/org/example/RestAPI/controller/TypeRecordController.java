package org.example.RestAPI.controller;

import org.example.RestAPI.request.typerecord.CreateTypeRecordRequest;
import org.example.RestAPI.service.ITypeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typerecord")
public class TypeRecordController {
    @Autowired
    ITypeRecordService typerecordService;

    @PostMapping("/create")
    public ResponseEntity createTypeRecord(@RequestBody CreateTypeRecordRequest request){
        return null;
    }
}
