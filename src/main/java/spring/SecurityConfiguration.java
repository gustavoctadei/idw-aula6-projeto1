/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import util.Md5PasswordEncoder;

/**
 *
 * @author Gustavo
 */

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    protected void configure(HttpSecurity http) throws Exception {
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//        encodingFilter.setEncoding("UTF-8");
//        encodingFilter.setForceEncoding(true);
//        
//        http.addFilterBefore(encodingFilter, BasicAuthenticationFilter.class);
        
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/publico/**").permitAll()
                .antMatchers("/logado/**").hasRole("USUARIO")
                .antMatchers("/admin/**").hasRole("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/publico/login.xhtml").defaultSuccessUrl("/logado/principal.xhtml")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/publico/login.xhtml");
        
        http.csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
    }
    
}
