package org.example.RestAPI.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignupRequest {
    private final int MAX_LENGTH = 20;
    private final int MIN_LENGTH = 6;
    private String user_name;
    private String password;
    private String result;

    public SignupRequest(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
        this.checkValidRequest();
    }

    private void checkValidRequest(){
        if (this.user_name.length() > MAX_LENGTH || this.user_name.length() < MIN_LENGTH){
            result = new String("")
        }
    }
}
