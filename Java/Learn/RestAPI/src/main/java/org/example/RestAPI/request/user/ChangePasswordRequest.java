package org.example.RestAPI.request.user;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private long id;
    private String old_password;
    private String new_password;
}
