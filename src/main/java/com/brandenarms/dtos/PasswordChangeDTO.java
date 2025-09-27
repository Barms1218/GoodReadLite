package com.brandenarms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordChangeDTO {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "New password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String newPassword;

    public PasswordChangeDTO() {

    }

    public PasswordChangeDTO(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
