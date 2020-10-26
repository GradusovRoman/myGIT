package ru.geekbrains.thirdquarter.springintro.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final ExtendedBCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(DataSource dataSource, ExtendedBCryptPasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO can't change the sequence
        http
                .authorizeRequests()
                .antMatchers("/", "/registration")
                .permitAll()
                .antMatchers("/products", "/products/showAll")
                .hasAnyRole("MANAGER", "USER", "ADMIN")
                .antMatchers("/products/**")
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/**")
                .hasRole("ADMIN")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT `username`, `password`, true FROM chat_server_db.users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT users.username as `username`, roles.`name` as `authority` from users\n" +
                        "JOIN users_roles ON users_roles.users_id = users.id \n" +
                        "JOIN roles ON roles.id = users_roles.roles_id WHERE users.username = ?");
    }
}
