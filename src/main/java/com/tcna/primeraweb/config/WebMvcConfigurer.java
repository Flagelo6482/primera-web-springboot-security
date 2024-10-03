package com.tcna.primeraweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    //Método para agregar/configurar VISTAS
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403"); //Cuando accedamos al endopoint "/403" mostraremos el archivo "403"
        //Cada vez que querramos agregar un FORMULARIO personalizado lo vamos a tener que hacer de esta manera
        registry.addViewController("/login").setViewName("login");
    }
}
