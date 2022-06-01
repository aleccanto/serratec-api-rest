package br.com.serratec.monitoria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSimpleEmail(String toEmail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toEmail);
		
		message.setFrom(from);
		message.setText(body);
		message.setSubject(subject);

		javaMailSender.send(message);

		System.out.println("E-mail simples enviado.");

	}
}
