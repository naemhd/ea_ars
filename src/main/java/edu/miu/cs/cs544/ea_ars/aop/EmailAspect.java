package edu.miu.cs.cs544.ea_ars.aop;

import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.PassengerDTO;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.jms.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class EmailAspect {
    @Autowired
    EmailService emailService;
    @AfterReturning(value = "execution(* edu.miu.cs.cs544.ea_ars.service.AirPortServiceImpl.*(..))")
    public void sendConfiramtionEmailAdmin(JoinPoint joinpoint) {
        new Thread() {
            public void run() {
                emailService.sendMail("samsonahmed@miu.edu");			}
        }.start();
        }

    @AfterReturning(value = "execution(* edu.miu.cs.cs544.ea_ars.service.PassengerServiceImpl.*(..)) && args(passengerDTO))")
    public void sendConfiramtionEmailForPassenger(JoinPoint joinpoint, PassengerDTO passengerDTO) {
        new Thread() {
            public void run() {
                List<Reservation> reservationDTO=passengerDTO.getReservations();

                String text=passengerDTO.getFirstName();
                if(reservationDTO.size()!=0){
                    text="  your reservation code is "+ reservationDTO.get(0).getReservationCode()+ " \n you reserved for " +reservationDTO.get(0).getReservationDate();
                }
                emailService.sendMailPassenger(passengerDTO.getEmail(),text);			}
        }.start();
    }
    }
