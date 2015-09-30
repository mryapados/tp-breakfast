package fr.treeptik.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fr.treeptik.service.LoginService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private LoginService loginService;
	
	@Autowired
    private DataSource dataSource;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService)
		.passwordEncoder( new ShaPasswordEncoder() );
	    auth
	          .jdbcAuthentication()
	          .dataSource(dataSource)
	          .usersByUsernameQuery("SELECT login, encrypted_password, enabled FROM user WHERE login=?")
	          .authoritiesByUsernameQuery("SELECT login, role FROM user WHERE login=?");
	}
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/*/**"
                , "*/js/*/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  	  http.csrf().disable().authorizeRequests()
  		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
  		.antMatchers("/app/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_APP')")
  		.and().formLogin()
  		.and().logout()
  			.logoutUrl("/logout");
    }

}