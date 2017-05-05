package com.cusonar.example.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cusonar.example.user.domain.User;

@Mapper
public interface UserMapper {
	public User readUser(String username);
	public List<String> readAuthority(String username);
}
