package vn.com.anhtraixunau.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.com.anhtraixunau.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@Order(1)
public class CustomerSpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	/*@Bean("CustomerPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication()
		//.withUser("hoapro").password(passwordEncoder().encode("321654"))
		//.passwordEncoder(new BCryptPasswordEncoder())
		//.withUser("hoapro").password("$2a$10$SXHNXudSgf0oe6PcXh2sluLP4169d.SGN5unmC3bxJIXe2yVwx3wq")
		//.authorities("CUSTOMER");
		//.roles("CUSTOMER");
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		http
			.antMatcher("/customer/**")
			.authorizeRequests()
				.antMatchers("/admin/**", "/resources/**").permitAll()
				//.anyRequest().authenticated()
				//.antMatchers("/customer/**").authenticated()
				.antMatchers("/customer/**").access("hasRole('CUSTOMER')")
				.and()
			//.formLogin().loginPage("/admin/login").permitAll();
			.formLogin()
				.loginPage("/customer/login").permitAll()
				.defaultSuccessUrl("/customer/dashboard", true)
				.failureUrl("/login?success=false")
				.and()
			.logout()
				.logoutUrl("/customer/logout")
				//.logoutSuccessUrl("/login?customerLogouted")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()	
			.exceptionHandling().accessDeniedPage("/customer/denied");
	}
}
