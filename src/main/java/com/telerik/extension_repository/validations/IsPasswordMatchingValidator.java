package com.telerik.extension_repository.validations;


import com.telerik.extension_repository.validations.annotations.IsPasswordMatching;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching, Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof RegisterUserModel){
            RegisterUserModel registrationModel = (RegisterUserModel) object;
            return registrationModel.getPassword().equals(registrationModel.getConfirmPassword());
        }

        return false;
    }
}
