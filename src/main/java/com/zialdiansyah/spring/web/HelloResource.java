package com.zialdiansyah.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.zialdiansyah.spring.Author;
import com.zialdiansyah.spring.Book;
import com.zialdiansyah.spring.config.ApplicationProperties;
import com.zialdiansyah.spring.service.EmailService;

@RestController
@RequestMapping("/v1")
public class HelloResource {

    private ApplicationProperties properties;
    private Book book;
    private Author author;
    private EmailService emailService;

    public HelloResource(ApplicationProperties properties, Author author, Book book, EmailService emailService) {
        super();
        this.properties = properties;
        this.author = author;
        this.book = book;
        this.emailService = emailService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Name: "+properties.getName()+", Currency: "+properties.getCurrency();
    }

    @GetMapping("/book")
    public String getBook() {
        return "Book<br> Title: "+book.getTitle()+", Author: "+book.getAuthor().getName();
    }

    @GetMapping("/author")
    public String getAuthorName() {
        return author.getName();
    }

    @GetMapping("/send-mail")
    public String sendMail() {
        try {
            emailService.sendMail("to@example.com", "Mail Subject", "This is my first email using JavaMailer");
            return "Mail sent";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
