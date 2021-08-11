package edu.miu.cs.cs544.ea_ars.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

@Aspect
public class ExceptionsAOP {

    @Around(value = "execution(* edu.miu.cs.cs544.ea_ars.controller.FlightController.*(..))")
   public ResponseEntity<?> ExceptionHandling(ProceedingJoinPoint call) throws Throwable {
       try{
           call.proceed();
       }
       catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
       return ResponseEntity.ok().body("Success!");
   }

}
