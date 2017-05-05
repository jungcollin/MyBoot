package com.cusonar.example.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cusonar.example.home.domain.Home;

@Mapper
public interface HomeMapper {

//	@Select("SELECT * FROM home WHERE name=#{name}")
	public Home readHome(@Param("name") String name);
}
