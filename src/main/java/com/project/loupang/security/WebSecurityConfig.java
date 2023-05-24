package com.project.loupang.security;


import com.project.loupang.oauth.jwt.JwtAuthenticationFilter;
import com.project.loupang.oauth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean   // 비밀번호 암호화
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override // Bean 에 등록
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // POST 요청에 대한 CSRF를 추가로 무시해 줘야 접근이 가능합니다.
        http.csrf().disable();
        // 시큐리티에 cors를 맞춘다
        http.cors();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                // api 요청 접근허용
                .antMatchers("/v1/**" ).permitAll()
                .antMatchers("**").permitAll()
                .antMatchers("/").permitAll()

                .antMatchers("/api/**").permitAll()
                .antMatchers("/pub/**").permitAll()
                .antMatchers("/sub/**").permitAll()

                .antMatchers(HttpMethod.POST,"/api/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()

                .antMatchers(HttpMethod.POST,"/chat/**").permitAll()
                .antMatchers(HttpMethod.GET,"/chat/**").permitAll()

                .antMatchers(HttpMethod.POST,"/room/**").permitAll()
                .antMatchers(HttpMethod.GET,"/room/**").permitAll()

                .antMatchers(HttpMethod.POST,"/pub/**").permitAll()
                .antMatchers(HttpMethod.GET,"/pub/**").permitAll()

                .antMatchers(HttpMethod.POST,"/sub/**").permitAll()
                .antMatchers(HttpMethod.GET,"/sub/**").permitAll()

                .antMatchers(HttpMethod.GET,"/ws-stomp/**").permitAll()
                .antMatchers(HttpMethod.POST,"/ws-stomp/**").permitAll()

                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()

                // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://13.125.244.223:8888");
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
