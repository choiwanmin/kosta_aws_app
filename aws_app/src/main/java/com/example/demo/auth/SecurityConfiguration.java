package com.example.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfiguration {
	@Bean // >>의존성 주입 어노테이션, 자동으로 필요한 객체 생성, 라이브러리 제공되는 객체들 생성을 원할때 사용
	// >>패스워드 인코딩 디코딩 같은 암호화 역할
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authentivationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	// >>필터의 한 종류
	// >>요청이 오면 거르는 역할 컨트롤 까지 가기 전에
	// >>하나가 아닌 여러개를 묶을 수 있음
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// >>체인, 각각의 메소드가 타겟 객체 반환 .으로 연속으로 접근
		http.httpBasic(HttpBasicConfigurer::disable).csrf(CsrfConfigurer::disable) // csrf은 post, put, delete 등의 요청 막음
																					// >>보안 관련, 악의적인 접근, 데이터 변경을 막기 위해,
																					// 겟 방식 요청을 제외한 나머지는 막음
				.cors(Customizer.withDefaults()) // >>ip 관련 보안, 크로스 오리진과 비슷한 역할
				.authorizeHttpRequests((authz) -> authz // >>인증관련 요청 처리
						.requestMatchers("/index_admin").hasRole("ADMIN")
						.requestMatchers("/index_seller").hasRole("SELLER")
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // forward 요청은 모두 인증 없이 허용>>포워드 요청을
																					// 다 허락 포워드는 서버 내에서만 이동, 인증 절차를 거치지
																					// 않고 다 허용, 리다이렉트 서버 밖에서 이동
//				.requestMatchers("/auth/**, /article/**, /index_/**").authenticated() //url이 /auth/로 시작하면 인증을 요구 >>authenticated은 인증 요구
						.requestMatchers("/auth/**", "/index_**").authenticated()
						.requestMatchers("/", "/join", "/error", "/login", "/idcheck").permitAll())
				.formLogin((login) -> login.loginPage("/loginform") // 로그인 폼 url 설정
						.loginProcessingUrl("/login") // 로그인 처리 url
						.usernameParameter("id") // 로그인 페이지에서 id 입력 양식의 이름
						.passwordParameter("pwd") // 로그인 페이지에서 pwd 입력 양식의 이름
						.defaultSuccessUrl("/", true).permitAll() // >>로그인 성공시 이동 페이지
						.successHandler(new MySuccessHandler()).failureHandler(new MyFailureHandler()));
		return http.build();
	}
}
