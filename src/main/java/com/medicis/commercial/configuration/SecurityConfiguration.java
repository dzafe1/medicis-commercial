
package com.medicis.commercial.configuration;


import com.medicis.commercial.service.MedicisUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MedicisUserDetailsService medicisUserDetailsService;



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("css/**","fonts/**","images/**","js/**").permitAll()
                .and().formLogin().loginPage("/login-register").permitAll().loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/?error=true")
                .and().logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().authorizeRequests().antMatchers("/appointments/**").hasAuthority("USER")
                .and().authorizeRequests().antMatchers("/user-profile").hasAuthority("USER")
                .and().authorizeRequests().antMatchers("/user-appointments").hasAuthority("USER")
                .and().authorizeRequests().antMatchers("/hospital-profile").hasAuthority("HOSPITAL")
                .and().authorizeRequests().antMatchers("/hospital-appointments").hasAuthority("HOSPITAL")
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .csrf();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(medicisUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
