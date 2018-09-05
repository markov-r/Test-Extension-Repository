package com.telerik.extension_repository.validations;


import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.validations.annotations.ExtendedEmailValidator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailMatchingValidator implements ConstraintValidator<ExtendedEmailValidator, Object> {
    @Override
    public void initialize(ExtendedEmailValidator isPasswordMatching) {

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
