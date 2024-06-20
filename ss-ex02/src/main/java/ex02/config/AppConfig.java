package ex02.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

import ex02.filter.MySecurityFilter01;
import ex02.filter.MySecurityFilter02;
import ex02.filter.MySecurityFilter03;
import ex02.filter.MySecurityFilter04;

@Configuration
public class AppConfig {
	@Bean
	public FilterChainProxy springSercurityFilterChain() {
		List<SecurityFilterChain> list = Arrays.asList(
				new SecurityFilterChain() {
					public boolean matches(HttpServletRequest request) {
						String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
						return new AntPathMatcher("/").match("/assets/**", uri);
					}
		
					public List<Filter> getFilters() {
						return null;
					}
		},
		
		new SecurityFilterChain() {

			public boolean matches(HttpServletRequest request) {
				String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
				return new AntPathMatcher("/").match("/**", uri);
			}

			public List<Filter> getFilters() {
				return Arrays.asList(
						mySeqFilter01(), 
						mySeqFilter02(), 
						mySeqFilter03(), 
						mySeqFilter04()
					);
				}
			}
		);
			
		return new FilterChainProxy(list);
	}

	@Bean
	public MySecurityFilter01 mySeqFilter01() {
		return new MySecurityFilter01();
	}

	@Bean
	public MySecurityFilter02 mySeqFilter02() {
		return new MySecurityFilter02();
	}

	@Bean
	public MySecurityFilter03 mySeqFilter03() {
		return new MySecurityFilter03();
	}

	@Bean
	public MySecurityFilter04 mySeqFilter04() {
		return new MySecurityFilter04();
	}
}
