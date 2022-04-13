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
@Order(2)
public class StaffSpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	/*@Bean("StaffPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication()
		//.withUser("hoadeptrai").password(passwordEncoder().encode("123"))
		//.passwordEncoder(new BCryptPasswordEncoder())
		//.withUser("hoadeptrai").password("$2a$10$hcyxMYPF4t3tTCfPK0QKvet30NeYQu3KDF1YZAk9PWthVMo.rRJw6")
		//.authorities("STAFF");
		//.roles("STAFF");
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		http
			.antMatcher("/staff/**")
			.authorizeRequests()
				.antMatchers("/admin/**", "/resources/**").permitAll()
				//.anyRequest().authenticated()
				//.antMatchers("/staff/**").authenticated()
				.antMatchers("/staff/**").access("hasAuthority('ADMIN 1')")
				.and()
			//.formLogin().loginPage("/admin/login").permitAll();
			.formLogin()
				.loginPage("/staff/login").permitAll()
				.loginProcessingUrl("/staff/login")
				.defaultSuccessUrl("/staff/dashboard", true)
				.failureUrl("/staff/login?isSuccess=false")
				.and()
			.logout()
				.logoutUrl("/staff/logout")
				//.logoutSuccessUrl("/login?staffLogouted")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			.exceptionHandling().accessDeniedPage("/staff/denied");
		}
}
