package com.cusonar.example.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import com.cusonar.example.user.domain.User;
import com.cusonar.example.user.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	UserMapper userMapper;
	
	@Test
	public void readUserTest() {
		User user = userMapper.readUser("cusonar");
		assertThat("cusonar", is(user.getUsername()));
		assertThat("YCU", is(user.getName()));
		assertThat("1234", is(user.getPassword()));
	}
	
	@Test
	public void readAuthorityTest() {
		List<GrantedAuthority> authorities = userMapper.readAuthority("cusonar");
		Iterator<GrantedAuthority> it = authorities.iterator();
		while(it.hasNext()) {
			GrantedAuthority authority = it.next();
			assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
		}
	}
}
