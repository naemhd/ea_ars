package edu.miu.cs.cs544.ea_ars.jms;

import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.AirPortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(String dataAdmin){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("samsonramato@gmail.com");
        message.setTo(dataAdmin);
        message.setSubject("Successfully changed database content for airport");
        message.setText("Data base content fot the airport is succesfully changed");
        javaMailSender.send(message);
        return "message sent successfully";
    }
}
