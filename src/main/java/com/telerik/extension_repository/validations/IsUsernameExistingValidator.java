package com.telerik.extension_repository.validations;


import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.validations.annotations.IsPasswordMatching;
import com.telerik.extension_repository.validations.annotations.IsUsernameExisting;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUsernameExistingValidator implements ConstraintValidator<IsUsernameExisting, Object> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(IsUsernameExisting isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof RegisterUserModel) {
            RegisterUserModel registrationModel = (RegisterUserModel) object;
            if (this.userRepository.findAll()
                    .stream()
                    .filter(user -> user.getUsername().equals(registrationModel.getUsername()))
                    .count() > 0) {
            }

            return false;
        }

        return true;
    }
}

