package ru.job4j.accident;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
    Класс только лишь помогает получить закодированное представление пароля с использованием BCryptPasswordEncoder
    Запускать приложение нужно с помощью tomcat, который создает контекст Spring и загружает DispatcherServlet.
 */
public class Encoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");
        System.out.println(pwd);
    }
}
