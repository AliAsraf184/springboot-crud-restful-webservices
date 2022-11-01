package com.example.testprojectfordeploy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException{

    public static final long serialVersionUId =1L;

    public ResourcesNotFoundException(String message){
         super(message);
    }
}
