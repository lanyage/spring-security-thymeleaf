package com.lanyage.springsecuritythymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity      //开启网络安全
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/home").permitAll()   //所有人都可以访问 / 和/home
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()    //所有人都可以访问/login
                .and()
                .logout().permitAll();  //所有人都可以退出登录
    }

    //配置Authentication来自于内存，当然也可以通过UserDetailsService指定Authentication的来源(DaoAuthenticationProvider)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptPasswordEncoder)
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .roles("USER");
        //auth.userDetailsService(userDetailsService()).configure(auth);
    }
}
