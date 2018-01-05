package com.tpg.ocs.context;

import com.tpg.ocs.web.security.OcsBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String REALM_NAME = "OCS";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void  configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
        		.antMatchers("/ocs", "/ocs/home", "/ocs/about").permitAll()
                .antMatchers(HttpMethod.POST, "/ocs/customers").hasRole("NEW_CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .httpBasic().realmName(REALM_NAME)
                    .authenticationEntryPoint(authenticationEntryPoint())
                .and().csrf().disable();
//            .formLogin().loginProcessingUrl("/shop/login");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return new OcsBasicAuthenticationEntryPoint();
    }
}
