package edu.miu.cs.cs544.ea_ars.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

@Aspect
public class ExceptionsAOP {

//    @Around("execution(*  *.*.*(..))")
//    public ResponseEntity<?> handleExceptional(ProceedingJoinPoint call) {
//        try {
//            call.proceed();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(call.getSignature().getName() + e.getMessage());
//        } catch (Throwable throwable) {
//            return ResponseEntity.badRequest().body(call.getSignature().getName() + throwable.getMessage());
//        }
//        return ResponseEntity.badRequest().body("Unknown");
//    }

    @AfterThrowing(pointcut = "execution(* edu.miu.cs.cs544.ea_ars.controller.FlightController.*(..))", throwing = "exception")
    public ResponseEntity<?> tracemethod(JoinPoint joinpoint, Exception exception) {
        return ResponseEntity.ok().body(exception.getMessage());
    }
}
