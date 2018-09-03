package com.telerik.extension_repository.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "No such user")
public class UserNotFoundException extends RuntimeException {
}
