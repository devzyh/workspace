package cn.devzyh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * 身份验证基本配置使用
 */
@Configuration
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置身份验证限制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单验证
                .and()
                .httpBasic(); // Base验证
        // 对所有请求进行验证
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置用户数据来源
     *
     * @param builder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication() // 采用内存方式验证
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) // 密码加密方式
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("demo").password("demo").roles("USER");
    }

}
