package com.example.customerms.exceptions.errorhandlers;

import com.example.customerms.exceptions.ResourceNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Aspect
@Component
public class ServiceErrorHandlerAspect {

//    @AfterThrowing(
//            pointcut = "execution( * com.example.customerms.service.CustomerService.*(..))",
//            throwing = "exception"
//    )

    @AfterThrowing(
            pointcut = "execution(* com.example.customerms.service.CustomerService.*(..))",
            throwing= "exception")
    public void handleServiceLayerErrors(JoinPoint jp, Exception exception) {
        if (exception instanceof EntityNotFoundException) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

}
