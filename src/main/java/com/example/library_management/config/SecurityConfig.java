package com.example.library_management.config;

import com.example.library_management.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/api/v1/auth/register/**").permitAll()
                 .anyRequest().authenticated()
                 .and().httpBasic().authenticationEntryPoint(entryPoint());;
    }

    @Bean
    public AuthenticationEntryPoint entryPoint() {
        return new BasicAuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                JSONObject jsonObject = new JSONObject();
                try {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    jsonObject.put("message", authException.getMessage());
                    response.getWriter()
                            .println(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterPropertiesSet() {
                setRealmName("Library-Management");
                super.afterPropertiesSet();
            }
        };
    }
}
