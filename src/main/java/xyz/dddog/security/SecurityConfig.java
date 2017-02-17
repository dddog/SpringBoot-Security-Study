package xyz.dddog.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public static final String REMEMBER_ME_KEY = "dddogKey";
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/index", "/").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/login").permitAll().failureUrl("/")
			.and()
			.rememberMe()
				.key(REMEMBER_ME_KEY)
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(86400) // 1 day
				.rememberMeServices(persistentTokenBasedRememberMeServices());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/h2-console/**");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices(){
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices
				= new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, jdbcTokenRepository());
		return persistentTokenBasedRememberMeServices;
	}

	@Bean
	public JdbcTokenRepositoryImpl  jdbcTokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setCreateTableOnStartup(false);
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices(){
		TokenBasedRememberMeServices tokenBasedRememberMeServices =
				new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
		tokenBasedRememberMeServices.setCookieName("dddogCookie");
		return tokenBasedRememberMeServices;
	}
}
