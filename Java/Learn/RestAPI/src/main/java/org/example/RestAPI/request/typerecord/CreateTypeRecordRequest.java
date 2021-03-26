package org.example.RestAPI.request.typerecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.RestAPI.finalstring.FinalMessage;

@Data
@AllArgsConstructor
public class CreateTypeRecordRequest {
    private final int MAX_LENGTH = 50;
    private String typerecord_name;
    private String image_url;
    private String result;

    public CreateTypeRecordRequest(String typerecord_name, String image_url){
        this.typerecord_name = typerecord_name;
        this.image_url = image_url;
        this.checkValidRequest();
    }

    private void checkValidRequest(){
        if (typerecord_name.length() > MAX_LENGTH){
            result = FinalMessage.INVALID_TYPERECORD_NAME_LENGTH;
            return;
        }
        result = new String("OK");
    }
}
