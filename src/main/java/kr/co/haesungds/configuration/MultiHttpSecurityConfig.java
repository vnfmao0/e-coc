package kr.co.haesungds.configuration;

import kr.co.haesungds.security.CustomAuthenticationProvider;
import kr.co.haesungds.security.CustomLoginSuccessHandler;
import kr.co.haesungds.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MultiHttpSecurityConfig {
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
//    }
    @Autowired
    private UserDetailsServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return new AES256PasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/home");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Configuration
    @Order(1)
    public class SloWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/assets/**", "/css/**", "/js/**", "/slo/**", "/img/**", "/fonts/**", "/favicon.ico", "/error", "/damoDownload.jsp/**", "/damoUpload.jsp/**", "/cmmfile/**","/report.jsp/**","/export/**","/font/**","/ico/**","/img/**","/js/**", "/*.jsp", "/movie/**", "/mp4Stream/**", "/onpage/mp4Stream/**", "/checkUserid/**", "/checkUseridProc/**");
        }

        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .antMatcher("/api/**")
//                    .authorizeRequests()
//                    .anyRequest().hasRole("ADMIN")
//                    .and()
//                    .httpBasic();
            http
                    .csrf().disable();

            http
                    .authorizeRequests()
                    .antMatchers("/sloLogin**", "/security/**", "/home").permitAll()
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();

            http.formLogin()
                    .loginPage("/sloLogin")
                    .loginProcessingUrl("/sloLoginProc")
                    //.successHandler(successHandler())
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/security/error").permitAll()
                    .usernameParameter("userid")
                    .passwordParameter("passwd")
                    .permitAll();

            http.logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/security/logoutProc")
                    .invalidateHttpSession(true);


            http.exceptionHandling()
                    //인증 실패시 로그인 페이지로
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                            //response.sendRedirect("/sloLogin?error=true&exception=" + authException.getMessage());
                            response.sendRedirect("/security/error?error=true&exception=" + authException.getMessage());
                        }
                    })
                    //인가 실패시 인가실패 에러페이지로
                    .accessDeniedPage("/security/denied");
        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //auth.userDetailsService(userService); //.passwordEncoder(passwordEncoder());
            auth.authenticationProvider(authenticationProvider());
        }
    }

//    @Configuration
//    public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring().antMatchers("/assets/**", "/css/**", "/js/**", "/slo/**", "/img/**", "/fonts/**", "/favicon.ico", "/error","/damoDownload.jsp/**", "/damoUpload.jsp/**", "/cmmfile/**");
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
////            http
////                    .authorizeRequests()
////                    .anyRequest().authenticated()
////                    .and()
////                    .formLogin();
//            http
//                    .csrf().disable();
//
//            http
//                    .authorizeRequests()
//                    .antMatchers("/security/*").permitAll()
//                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
//                    .anyRequest().authenticated();
//
//            http.formLogin()
//                    .loginPage("/security/login")
//                    .loginProcessingUrl("/security/login")
//                    .defaultSuccessUrl("/home")
//                    //.successHandler(successHandler())
//                    .failureUrl("/security/error").permitAll()
//                    .usernameParameter("userid")
//                    .passwordParameter("passwd")
//                    .permitAll();
//
//            http.logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/security/logoutProc")
//                    .invalidateHttpSession(true);
//
//
//            http.exceptionHandling()
//                    //인증 실패시 로그인 페이지로
//                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                        @Override
//                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                            //response.sendRedirect("/sloLogin?error=true&exception=" + authException.getMessage());
//                            authException.printStackTrace();
//                            response.sendRedirect("/security/error?error=true&exception=" + authException.getMessage());
//                        }
//                    })
//                    //인가 실패시 인가실패 에러페이지로
//                    .accessDeniedPage("/security/denied");
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            //auth.userDetailsService(userService); //.passwordEncoder(passwordEncoder());
//            auth.authenticationProvider(authenticationProvider());
//        }
//    }
}
