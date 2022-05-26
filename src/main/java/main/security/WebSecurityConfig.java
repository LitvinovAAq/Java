package main.security;

import main.security.jwt.JwtSecurityConfigurer;
import main.security.jwt.JwtTokenProvider;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().
                csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/autoTable").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/journalTable").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/routesTable").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/autoPersonnelTable").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/autoPersonnelTable/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/autoTable/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/journalTable/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/carPark/routesTable/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/autoPersonnelTable/byJournalId").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/autoPersonnelTable/byRouteId").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/autoPersonnelTable/ltDate").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/autoPersonnelTable/gtDate").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/autoTable/byJournalId").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/routesTable/byJournalId").permitAll()
                .antMatchers(HttpMethod.POST, "/carPark/routesTable/byAutoId").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

    }
}
