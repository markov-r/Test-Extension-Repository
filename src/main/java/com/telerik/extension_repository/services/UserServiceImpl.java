package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.exceptions.UserNotFoundException;
import com.telerik.extension_repository.models.bindingModels.user.*;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private AuthorityService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(EditUserModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        if(this.userRepository.findAll().isEmpty()) {
            user.setAuthorities(this.getAuthorities("ROLE_ADMIN"));
        } else {
            user.setAuthorities(this.getAuthorities("ROLE_USER"));
        }

//        AuthorityModel userRole = this.roleService.findByName("ROLE_USER");
//        Authority role = this.modelMapper.map(userRole, Authority.class);
//        user.addRole(role);
        this.userRepository.save(user);
    }



    @Override
    public List<EditUserModel> getAll() {
        List<User> users = this.userRepository.findAll();
        List<EditUserModel> userModelViews = new ArrayList<>();
        for (User user : users) {
            EditUserModel userModelView = this.modelMapper.map(user, EditUserModel.class);
            userModelViews.add(userModelView);
        }
        return userModelViews;
    }

    @Override
    public EditUserModel getById(Long id) {
        User user = this.userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        EditUserModel userModelView = this.modelMapper.map(user, EditUserModel.class);
        return userModelView;
    }

    @Override
    public boolean isEnabled(Long id) {
        User user = this.userRepository.findById(id);
        return user.isEnabled();
    }

    @Override
    public List<ExtensionStatusView> getOwnsExtensions(Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findOneByUsername(username);
    }

    @Override
    public void edit(EditUserModel editUserModel) {

    }

    @Override
    public LoggedUser getByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username,password);
        ModelMapper modelMapper = new ModelMapper();
        LoggedUser loggedUser = null;
        if (user != null) {
            loggedUser = modelMapper.map(user,LoggedUser.class);
        }
        return loggedUser;
    }

    @Override
    public LoginUser getByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        LoginUser loginUser = null;
        if (user != null){
            loginUser = modelMapper.map(user, LoginUser.class);
        }
        return loginUser;
    }

    @Override
    public EditUserModel getUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        EditUserModel userByName = null;
        if (user != null){
            userByName = modelMapper.map(user, EditUserModel.class);
        }
        return userByName;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = this.userRepository.findById(id);
        this.userRepository.delete(user);
    }

    private Set<Authority> getAuthorities(String authority) {
        Set<Authority> userAuthorities = new HashSet<>();

        userAuthorities.add(this.authorityRepository.getByAuthority(authority));

        return userAuthorities;
    }

    @Override
    public void disableUser(Long id) {
        this.userRepository.update(id);
    }

    private String getUserAuthority(String userId) {
        return this
                .userRepository
                .findById(userId)
                .get()
                .getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority();
    }
}
