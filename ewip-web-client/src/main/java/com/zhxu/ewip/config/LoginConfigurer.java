package com.zhxu.ewip.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class LoginConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/login").permitAll().and()
//                .authorizeRequests().antMatchers("/error").permitAll().and().
//                authorizeRequests().anyRequest()
//				.authenticated().and().csrf().disable();
//				.csrfTokenRepository(csrfTokenRepository()).and()
//				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
//				.logout().logoutUrl("/logout").permitAll()
//				.logoutSuccessUrl("/");
		http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.anyRequest()
				.authenticated();
	}

//	private Filter csrfHeaderFilter() {
//		return new OncePerRequestFilter() {
//			@Override
//			protected void doFilterInternal(HttpServletRequest request,
//											HttpServletResponse response, FilterChain filterChain)
//					throws ServletException, IOException {
//				CsrfToken csrf = (CsrfToken) request
//						.getAttribute(CsrfToken.class.getName());
//				if (csrf != null) {
//					Cookie cookie = new Cookie("XSRF-TOKEN",
//							csrf.getToken());
//					cookie.setPath("/");
//					response.addCookie(cookie);
//				}
//				filterChain.doFilter(request, response);
//			}
//		};
//	}
//
//	private CsrfTokenRepository csrfTokenRepository() {
//		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//		repository.setHeaderName("X-XSRF-TOKEN");
//		return repository;
//	}
}