package com.zialdiansyah.spring.config;

import jakarta.mail.*;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zialdiansyah.spring.Author;
import com.zialdiansyah.spring.Book;
import com.zialdiansyah.spring.service.EmailService;

@ComponentScan(basePackages = {"com.zialdiansyah.spring"})
@Configuration
public class AppConfig {

    @Bean
    public Author author1() {
        Author author = new Author();
        author.setId(1);
        author.setName("Steven");
        return author;
    }

    @Bean
    public Book book1(Author author1) {
        Book book = new Book("Belajar Java", author1);
        return book;
    }

    @Bean
    public Properties mailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        prop.put("mail.smtp.port", 465);
        return prop;
    }

    @Bean
    public PasswordAuthentication passwordAuthentication() {
        return new PasswordAuthentication("a4dc0dccc95989", "be410cb12c087a");
    }

    @Bean
	public Session mailSession(@Qualifier("mailProperties") Properties mailProperties, PasswordAuthentication passwordAuthentication) {
		return Session.getInstance(mailProperties, new Authenticator() {
			@Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return passwordAuthentication;
		    }
		});
	}

    @Bean
	public EmailService emailService(Session mailSession) {
		return new EmailService(mailSession);
	}
}
