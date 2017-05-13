package com.cusonar.example.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.cusonar.example.user.domain.User;
import com.cusonar.example.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	private User user1;
	
	@Before
	public void setUp() {
        user1 = new User();
        user1.setUsername("cusonar");
        user1.setPassword("1234");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("ABC");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER", "ADMIN"));
	}
	
	@Test
	public void createUserTest() {
		
		userService.deleteUser(user1.getUsername());
		userService.createUser(user1);
		User user = userService.readUser(user1.getUsername());
		assertThat(user.getUsername(), is(user1.getUsername()));
		
		PasswordEncoder passwordEncoder = userService.passwordEncoder();
		assertThat(passwordEncoder.matches("1234",user.getPassword()), is(true));
		
		Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
		Iterator<? extends GrantedAuthority> it = authorities1.iterator();
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
		while(it.hasNext()) {
			GrantedAuthority authority = it.next();
			assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
		}
	}
}
