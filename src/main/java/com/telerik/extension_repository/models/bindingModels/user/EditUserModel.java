package com.telerik.extension_repository.models.bindingModels.user;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.utils.Constants;

import java.util.Set;
import java.util.stream.Collectors;

public class EditUserModel {
    private Long id;
    private String username;
    private String password;

    private Boolean isEnabled;
    private Set<Authority> authorities;

    public Long getId() {
        return id;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
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
