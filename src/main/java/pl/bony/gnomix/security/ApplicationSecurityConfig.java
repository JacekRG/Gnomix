package pl.bony.gnomix.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.bony.gnomix.security.CustomAuthenticationFilter;
import pl.bony.gnomix.security.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private PasswordEncoder encoder;
    private UserDetailsService userDetailService;
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder encoder, UserDetailsService service) {
        this.encoder = encoder;
        this.userDetailService = service;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new CustomAuthorizationFilter(), CustomAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/v3/api-docs/*", "/login", "/api/login/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("jacekrg")
//                .password(this.encoder.encode("alamakota2@A"))
//                .roles("MANAGER").build();
//
//        UserDetails user2 = User.builder()
//                .username("ali")
//                .password(this.encoder.encode("ali"))
//                .roles("RECEPTION").build();
//
//        return new InMemoryUserDetailsManager(user, user2);
//    }