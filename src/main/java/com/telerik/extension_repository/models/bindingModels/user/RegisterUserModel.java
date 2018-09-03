package com.telerik.extension_repository.models.bindingModels.user;


import com.telerik.extension_repository.validations.annotations.IsPasswordMatching;

import javax.validation.constraints.Size;

@IsPasswordMatching
public class RegisterUserModel {
    @Size(min = 5, max = 20, message = "Username should be between 5 and 20 symbols")
    private String username;

    @Size(min = 10, message = "Email too short")
    private String email;

    @Size(min = 5, max = 30, message = "Password should be between 5 and 30 symbols")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
