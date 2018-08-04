package com.bridgeit.TradeFinanceApp.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class SendMail {
	
	@Autowired
	private MailSender mailSender;  
	
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    }
    
    public boolean sendMail(String from, String to, String subject, String msg) {  
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom(from);  
        message.setTo(to);  
        message.setSubject(subject);  
        message.setText(msg);  
        mailSender.send(message);     
        return true;
    }
}
