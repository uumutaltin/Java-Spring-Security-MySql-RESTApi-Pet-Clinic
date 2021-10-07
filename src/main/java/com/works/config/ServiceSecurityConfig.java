package com.works.config;

import com.works.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService uService;
    public ServiceSecurityConfig(UserService uService) {
        this.uService = uService;
    }

    // sql -> jpa query -> user control
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService).passwordEncoder(uService.encoder());
    }

    // hangi yöntemle giriş yapılarak, rollere göre hangi servis kullanılcak?
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()

        .antMatchers("/bill/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/box/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/calendarinfo/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/category/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/customer/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/diary/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/image/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/lab/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/password/**").permitAll()
        .antMatchers("/petColor/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/petRace/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/pet/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/product/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/productstock/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/schedule/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/statistic/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/suppliers/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/user/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
        .antMatchers("/warehouse/**").hasAnyRole("ADMIN","DOKTOR","SEKRETER")
                .and()
        .csrf().disable()
        .formLogin().disable()
        .logout().logoutUrl("/user/logout").invalidateHttpSession(true) ;
        http.headers().frameOptions().disable(); // h2-console for using

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
