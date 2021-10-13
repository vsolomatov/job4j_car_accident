package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.DataConfig;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/*
    Когда tomcat загружает наше приложение, то он ищет класс, который расширяет WebApplicationInitializer.
    По сути этот класс заменяет собой файл web.xml
 */
public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        // Подключим класс конфигурации
        //ac.register(WebConfig.class, JdbcConfig.class);
        //ac.register(WebConfig.class, HbmConfig.class);
        ac.register(WebConfig.class, DataConfig.class, SecurityConfig.class);
        ac.refresh();

        // Создаем и добавляем фильтр, который будет заниматься кодированием
        //      (будет преобразовывать текст в кодировку UTF-8. Без этого кириллица будет выглядеть "крякозаброй").
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        FilterRegistration.Dynamic encoding = servletCxt.addFilter("encoding", filter);
        encoding.addMappingForUrlPatterns(null, false, "/*");

        // Создаем DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/"); // В данном случае DispatcherServlet будет обрабатывать все запросы
                                                // Когда на DispatcherServlet приходит запрос, он ищет подходящий контроллер
    }
}
