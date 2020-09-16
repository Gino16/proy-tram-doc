package com.example.demo;

import com.example.demo.auth.handler.LoginSuccesHandler;
import com.example.demo.models.service.usuario.SUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LoginSuccesHandler succesHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SUsuario usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**",
                        "/js/**",
                        "/imagenes/**").permitAll()
                .antMatchers("/").hasAnyRole("INVITED")
                .antMatchers("/index").hasAnyRole("INVITED")
                .antMatchers("/solicitud/listar").hasAnyRole("INVITED")
                .antMatchers("/solicitud/ver/**").hasAnyRole("INVITED")
                .antMatchers("/archivo/**").hasAnyRole("INVITED")
                .antMatchers("/voucher/ver/**").hasAnyRole("INVITED")
                .antMatchers("/voucher/verPdf/**").hasAnyRole("INVITED")
                .antMatchers("/solicitud/**").hasAnyRole("USER")
                .antMatchers("/persona/**").hasAnyRole("USER")
                .antMatchers("/voucher/**").hasAnyRole("USER")
                .antMatchers("/solicitud/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(succesHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");
    }



    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);

        /*PasswordEncoder encoder = this.passwordEncoder;

        User.UserBuilder users = User.builder().passwordEncoder(password -> {
            return encoder.encode(password);
        });

        builder.inMemoryAuthentication()
                .withUser(users.username("secre").password("12345").roles("USER", "INVITED"))
                .withUser(users.username("otrasecre").password("12345").roles("INVITED"));*/
    }
}
