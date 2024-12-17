package com.security.spring_security.config;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//Anotación para habilitar la seguridad web
@EnableWebSecurity
//Anotación para habilitar la seguridad de los métodos y hacer condifuraciones con metodos
@EnableMethodSecurity
public class SecurityConfig {

    //Anotación para crear un bean de seguridad
    @Bean
    //Metodo para configurar la cadena de filtros de seguridad o SecurityFilterChain
    public SecurityFilterChain securityFilterChain(/*Objeto que pasa por todos los filtros*/HttpSecurity httpSecurity) throws Exception {
        
        return httpSecurity.build();
    }

    //definimios el authenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //devolver proveedores de autenticación recordar que pueden ser varios
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //para poder extraer los usuarios de la BD
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //para poder encriptar la contraseña
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        //para poder extraer los usuarios de la BD
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //creando un usuario para simular que esta en la BD 
        //para que spring trabaje los usuarios de la BD y asi es como los trabaja
        //UserDetails userDetails = User.withUsername("user").password("1234").roles("ADMIN").authorities("READ", "CREATE").build();
        //para poder guardar en memoria

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("user1").password("1234").roles("ADMIN").authorities("READ", "CREATE").build());
        userDetailsList.add(User.withUsername("user2").password("1234").roles("USER").authorities("READ").build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public  PasswordEncoder passwordEncoder() {
        //retornar un password no codificada (solo PARA PRUEBAS)
        return NoOpPasswordEncoder.getInstance();
    }

}