package edu.miu.cs.cs544.ea_ars.aop;

import edu.miu.cs.cs544.ea_ars.jms.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class EmailAspect {
    @Autowired
    EmailService emailService;
    @AfterReturning(value = "execution(* edu.miu.cs.cs544.ea_ars.service.AirPortServiceImpl.*(..))")
    public void sendConfiramtionEmail(JoinPoint joinpoint) {
        new Thread() {
            public void run() {
                emailService.sendMail("samsonahmed@miu.edu");			}
        }.start();
        }
    }