package org.example.RestAPI.request.user;

import lombok.Data;
import org.example.RestAPI.finalstring.FinalMessage;

@Data
public class ChangePasswordRequest {
    private final int MAX_LENGTH = 20;
    private final int MIN_LENGTH = 6;
    private long id;
    private String old_password;
    private String new_password;
    private String result;



    private void checkValidRequest(){
        if (this.password.length() > MAX_LENGTH || this.password.length() < MIN_LENGTH){
            result = FinalMessage.INVALID_PASSWORD_LENGTH;
            return;
        }
        for (int i=0; i<this.password.length(); i++){
            int val = (int) (this.password.charAt(i));
            if (!checkRange(val)){
                result = FinalMessage.INVALID_PASSWORD_VALUE;
                return;
            }
        }

        result = new String("OK");
    }

    private boolean checkRange(int val){
        //TỪ O ĐẾN 9: 48-57
        if (val >= 48 && val <= 57){
            return true;
        }
        //Từ A đến Z: 65-90
        if (val >= 65 && val <= 90){
            return true;
        }
        //Từ a đến Z: 97-122
        if (val >= 97 && val <= 122){
            return true;
        }
        return false;
    }
}
