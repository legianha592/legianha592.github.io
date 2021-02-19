package org.example.RestAPI.request.user;

import lombok.Data;

@Data
public class SignupRequest {
    private String user_name;
    private String password;
}
