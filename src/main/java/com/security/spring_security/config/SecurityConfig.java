package com.security.spring_security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    // @Bean
    // //Metodo para configurar la cadena de filtros de seguridad o SecurityFilterChain
    // public SecurityFilterChain securityFilterChain(/*Objeto que pasa por todos los filtros*/HttpSecurity httpSecurity) throws Exception {
    //     //aqui se definen las condiciones de seguridad
    //     return httpSecurity
    //         .csrf(csrf -> csrf.disable())
    //         //para loguearnos usando user and password
    //         .httpBasic(Customizer.withDefaults())
    //         //para que no se guarde la sesión en la BD para eliminar token de autorizacion
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         //el endpoint hello se le permitira a todo mundo 
    //         .authorizeHttpRequests(http -> /*cubrir endpoint*/{
    //             //configurando endpoints publicos
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
    //             // condifugrar endpoints privados
    //             //el endpoint hello-secured tiene que tener el permiso de lectura
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("CREATE");
    //             //configurar endpoints no especificados o resto
    //             //cualquier otro request diferente e los de arriba
    //             http.anyRequest().denyAll(); //o authenticated() para los autenticados mientras que danyAll denegara el permiso a todos
    //         })
    //     .build();
    // }

    //trabajar con anotaciones de seguridad
    @Bean
     //Metodo para configurar la cadena de filtros de seguridad o SecurityFilterChain
    public SecurityFilterChain securityFilterChain(/*Objeto que pasa por todos los filtros*/HttpSecurity httpSecurity) throws Exception {
         //aqui se definen las condiciones de seguridad
         return httpSecurity
             .csrf(csrf -> csrf.disable())
             //para loguearnos usando user and password
             .httpBasic(Customizer.withDefaults())
             //para que no se guarde la sesión en la BD para eliminar token de autorizacion
             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             
         .build();
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