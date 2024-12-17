package com.security.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//Anotación para habilitar la seguridad web
@EnableWebSecurity
//Anotación para habilitar la seguridad de los métodos y hacer condifuraciones con metodos
@EnableMethodSecurity
public class SecurityConfig {

    //Anotación para crear un bean de seguridad
    @Bean
    //Metodo para configurar la cadena de filtros de seguridad
    public SecurityFilterChain securityFilterChain(/*Objeto que pasa por todos los filtros*/HttpSecurity httpSecurity) throws Exception {
        
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //devolver proveedir
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //para poder extraer los usuarios de la BD
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //para poder encriptar la contraseña
        authenticationProvider.setPasswordEncoder(null);
        //para poder extraer los usuarios de la BD
        authenticationProvider.setUserDetailsService(null);
        
        return authenticationProvider;

    }

}