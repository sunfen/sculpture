package cn.sf.sculpture.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
		
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				
				
				// SockJS security configuration. 
				// Set X-Frame-Options to SAMEORIGIN and disable CSRF Token requiring 
				http.headers()
					.frameOptions()
					.sameOrigin();
				
				http.csrf()
                .ignoringAntMatchers("/login/**", "/login**")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
				
				http.authorizeRequests()
					.antMatchers("/login**").permitAll()
					.antMatchers("/login/**").permitAll()
					.antMatchers(
							"/",
							"/project/**").authenticated()
							
					.and()
						//自定义登录
						.formLogin()
		                .failureUrl("/loginPage?error")
					.and()
				        .logout()
				        .logoutSuccessUrl("/loginPage?logout")
				        .clearAuthentication(true)
				        .invalidateHttpSession(true);
		
				    // session管理  
				    http.sessionManagement()
				        .sessionFixation()
				        .changeSessionId()
				        .maximumSessions(1);
			}
			
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			    PasswordEncoder pwd = new PasswordEncoder(){
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return (String)rawPassword;
                    }
                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return encodedPassword.equals((String)rawPassword);
                    }
            };
            
            auth.eraseCredentials(false)
                .userDetailsService(userDetailsService)
                .passwordEncoder(pwd);
			}
	}
