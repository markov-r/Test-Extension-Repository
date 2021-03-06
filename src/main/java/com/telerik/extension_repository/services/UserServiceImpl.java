package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.exceptions.UserNotFoundException;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.bindingModels.user.*;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    public void register(RegisterUserModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        if (this.userRepository.findAll().isEmpty()) {
            user.setAuthorities(this.getAuthorities("ROLE_ADMIN"));
        } else {
            user.setAuthorities(this.getAuthorities("ROLE_USER"));
        }

        this.userRepository.save(user);
    }

    @Override
    public User registerByUsernameAndPassword(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        return this.userRepository.save(user);
    }

    @Override
    public List<RegisterUserModel> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, RegisterUserModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegisterUserModel getById(Long id) {
        User user = this.userRepository.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return this.modelMapper.map(user, RegisterUserModel.class);
    }

    @Override
    public boolean isEnabled(Long id) {
        User user = this.userRepository.findById(id);
        return user.isEnabled();
    }

    public User getCurrentUser() {
        if (!(SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken)) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            return this.userRepository.findOneByUsername(principal.getUsername());
        }
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return this.userRepository.existsById(String.valueOf(id));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findOneByUsername(username);
    }

    @Override
    public void edit(RegisterUserModel registerUserModel) {
        User user = this.modelMapper.map(registerUserModel, User.class);
        user.setUsername(registerUserModel.getUsername());
        user.setEmail(registerUserModel.getEmail());
        user.setPassword(registerUserModel.getPassword());
       // this.userRepository.update(registerUserModel.getUsername(), registerUserModel.getEmail(), registerUserModel.getPassword(), registerUserModel.getId());
    }

    @Override
    public LoggedUser getByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        ModelMapper modelMapper = new ModelMapper();
        LoggedUser loggedUser = null;
        if (user != null) {
            loggedUser = modelMapper.map(user, LoggedUser.class);
        }
        return loggedUser;
    }

    @Override
    public LoginUser getByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        LoginUser loginUser = null;
        if (user != null) {
            loginUser = modelMapper.map(user, LoginUser.class);
        }
        return loginUser;
    }

    @Override
    public RegisterUserModel getUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        RegisterUserModel userByName = null;
        if (user != null) {
            userByName = modelMapper.map(user, RegisterUserModel.class);
        }
        return userByName;
    }


    @Override
    public List<RegisterUserModel> getAllNonAdminUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, RegisterUserModel.class))
                .filter(user -> !user.isAdmin())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExistUsername(String username) {
        User user = userRepository.findOneByUsername(username);

        if (user == null) {
            return false;
        }

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public List<RegisterUserModel> getAllByUsername(String username) {
        return this.userRepository.findAllByUsername(username)
                .stream()
                .map(user -> this.modelMapper.map(user, RegisterUserModel.class))
                .collect(Collectors.toList());
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
        this.userRepository.disableUser(id);
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

    @Override
    public RegisterUserModel getUserById(Long id) {
        return this.modelMapper.map(userRepository.getUserById(id), RegisterUserModel.class);
    }
}
