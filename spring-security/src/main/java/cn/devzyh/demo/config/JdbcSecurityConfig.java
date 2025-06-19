package cn.devzyh.demo.config;

import cn.devzyh.demo.service.SysAuthenticationProvider;
import cn.devzyh.demo.service.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 基于数据库定制身份验证功能
 */
@Configuration
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SysUserDetailsService userDetailsService;
    @Autowired
    SysAuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置验证数据来源
     *
     * @param auth
     * @throws Exception
     */
    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 配置请求处理方式
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/admin").hasRole("ROLE")
                .mvcMatchers("/user").hasRole("ROLE")
                .anyRequest().permitAll();
    }
}
