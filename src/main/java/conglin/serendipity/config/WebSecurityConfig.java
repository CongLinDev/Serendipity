package conglin.serendipity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    @Qualifier("serendipperServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/index","/account/**","/static/**").permitAll() //指定了/和/index 以及/static/**不需要任何认证就可以访问
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .failureUrl("/account/login?error=true")
                .usernameParameter("email")//用户名为邮箱
                .passwordParameter("password")
                .defaultSuccessUrl("/serendipper")//登陆成功后跳转到 /serendipper
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/index")
                .permitAll();
    }
}