package com.telerik.extension_repository.models.bindingModels.user;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.utils.Constants;
import com.telerik.extension_repository.validations.annotations.ExtendedEmailValidator;
import com.telerik.extension_repository.validations.annotations.IsPasswordMatching;
import com.telerik.extension_repository.validations.annotations.IsUsernameExisting;

import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@IsUsernameExisting
@ExtendedEmailValidator
@IsPasswordMatching
public class RegisterUserModel {

    private Long id;
    @Size(min = 5, max = 20, message = "Username should be between 5 and 20 symbols")
    private String username;

    @Size(min = 10, message = "Please enter valid email")
    private String email;

    @Size(min = 5, max = 30, message = "Password should be between 5 and 30 symbols")
    private String password;

    private String confirmPassword;

    private Boolean isEnabled;

    private Set<Authority> authorities;

    public RegisterUserModel() {
    }

    public RegisterUserModel(String username, String email, boolean isEnabled) {
        this.username = username;
        this.email = email;
        this.isEnabled = isEnabled;
    }

    public Long getId() {
        return id;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAdmin(){
        Set<String> names = this.getAuthorities()
                .stream()
                .map(n -> n.getAuthority())
                .collect(Collectors.toSet());
        return  names.contains(Constants.ADMIN_ROLE);
    }
}
