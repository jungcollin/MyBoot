package com.cusonar.example.user.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthenticationToken {

	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private String token;
	
	public AuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities, String token) {
		this.username = username;
		this.authorities = authorities;
		this.token = token;
	}
}
