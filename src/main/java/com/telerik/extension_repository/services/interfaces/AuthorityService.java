package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.models.bindingModels.user.AuthorityModel;

import java.util.List;
import java.util.Set;

public interface AuthorityService {
    AuthorityModel findByName(String name);
    List<AuthorityModel> getAll();
    Set<AuthorityModel> getAllByName(String[] names);
}
