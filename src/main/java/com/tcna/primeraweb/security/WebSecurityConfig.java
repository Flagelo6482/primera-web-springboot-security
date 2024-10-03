package com.tcna.primeraweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration //Registra beans
@EnableWebSecurity //Para habilitar la seguridad
public class WebSecurityConfig {

    /*
    @Bean //Para registrar este codificador en la fabrica de SPRING
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Bean //Registra un bean en el contenedor de spring
    public InMemoryUserDetailsManager userDetailsManager() {
        //Usuarios en memoria, registrados por defecto
        //Las contraseñas deben estar encriptadas si o si
        UserDetails user1 = User.builder()
                .username("user1")
                .password("$2a$10$rarhLceGOgOSrVSj7sQiZOD25S7ch/uDBoSKp7QAA8a64DohVOevG")
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("admin1")
                .password("$2a$10$rarhLceGOgOSrVSj7sQiZOD25S7ch/uDBoSKp7QAA8a64DohVOevG")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
     */

    //Nos indica que la seguridad de spring boot trabaje con una base de datos
    @Autowired
    private DataSource dataSource;

    /*
    * Método para configurar todo el proceso de autenticación, basada en JDBC, donde obtiene los nombres, datos de una
    * base de datos, para ellos necesitamos el codificador de bycript
    * */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                //Como vamos a obtener los username de el "dataSource" que apunta a nuestra base de datos, para cargar los usuarios
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?"); //Obtenemos los roles
    }


    //Filtro!!!, encargado de la parte de autorización y autenticación
    @Bean
    //Clase que nos permite acceder a las RUTAS, etc
    //El metodo HttpSecurity tiene metodos para acceder a endpoints, para manejar las excepciones, etc; sobre tódo estableciendo la autorización como abajo
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Indicamos que endpoints vamos a autorizar
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/animales").permitAll() //Este endpoint será disponible para todos los usuarios que ingresen a la página
                        .requestMatchers("/animales/nueva").hasAnyRole("ADMIN") //Solo a las personas que tengan el rol de ADMIN tienen acceso a este endpoint
                        .requestMatchers("/animales/editar/*","/animales/eliminar/*").hasRole("ADMIN") //El "*" es un parametro, donde el ADMIN tiene acceso
                        .anyRequest().authenticated()) //Cualquier otra petición que hagamos estara autenticada
                .formLogin(form -> form     //Indicamos el formulario para logearnos
                        .loginPage("/login")
                        .permitAll()) //Vamos a permitir a todos a hacer "login"
                .logout(l -> l.permitAll()) //Cerrado de sesión permitido para todos
                .exceptionHandling(e -> e.accessDeniedPage("/403")); //Excepcion donde nos envia al endopoint 403(archivo)
        return httpSecurity.build();
    }
}
