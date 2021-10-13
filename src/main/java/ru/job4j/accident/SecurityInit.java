package ru.job4j.accident;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
    За счет добавления этого класса происходит подключение фильтра авторизации

    Он подключает DelegatingFilterProxy. Это главный фильтр, в котором идет обработка запросов.

    Tomcat автоматически определяет такой класс и подключает эти фильтры.
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
