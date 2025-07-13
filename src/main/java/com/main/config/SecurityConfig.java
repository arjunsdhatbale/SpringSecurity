package com.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity
         	.csrf(customizer -> customizer.disable())
         	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/user/register", "/user/login").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            		.anyRequest().authenticated()
            )
      //  httpSecurity.formLogin(Customizer.withDefaults())
//          .httpBasic(Customizer.withDefaults())
         
         .authenticationProvider(authenticationProvider())
         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
         .build();

         
    }
 
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Throwable {
    	
     	return authenticationConfiguration.getAuthenticationManager();
    	
    }
    
    
    
    
//  @Bean
//  public UserDetailsService userDetailsService( ){
//
//      UserDetails user1 = User.withDefaultPasswordEncoder()
//              .username("vikas")
//              .password("v@123")
//              .roles("ADMIN")
//              .build();
//
//      UserDetails user2 = User.withDefaultPasswordEncoder()
//              .username("kiran")
//              .password("k@123")
//              .roles("ADMIN")
//              .build();
//      return new InMemoryUserDetailsManager(user1,user2);
//
//  }
    
    
    
    
    
    
    
    
    
    

}
