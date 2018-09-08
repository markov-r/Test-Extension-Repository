package com.telerik.extension_repository.services;


import com.telerik.extension_repository.services.interfaces.AuthorityService;
import org.modelmapper.ModelMapper;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.models.bindingModels.user.AuthorityModel;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorityModel findByName(String name) {
        Authority role =  roleRepository.getByAuthority(name);
        return this.modelMapper.map(role, AuthorityModel.class);
    }

    @Override
    public List<AuthorityModel> getAll() {
        List<Authority> roles = this.roleRepository.findAll();
        List<AuthorityModel> roleModels = new ArrayList<>();
        for (Authority role : roles) {
            AuthorityModel roleModel = this.modelMapper.map(role, AuthorityModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }

    @Override
    public Set<AuthorityModel> getAllByName(String[] names) {
        Set<Authority> roles = new HashSet<>(this.roleRepository.findAll());
        Set<AuthorityModel> roleModels = new HashSet<>();
        for (Authority role : roles) {
            AuthorityModel roleModel = this.modelMapper.map(role, AuthorityModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }
}
