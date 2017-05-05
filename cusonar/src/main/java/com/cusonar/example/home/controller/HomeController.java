package com.cusonar.example.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cusonar.example.home.domain.Home;
import com.cusonar.example.home.mapper.HomeMapper;

@RestController
public class HomeController {
	
	@Autowired
	HomeMapper homeMapper;

	@RequestMapping("/{name}/{message}")
	public Home homeee(@PathVariable String name, @PathVariable String message) {
		
		Home home = new Home();
		home.setName(name);
		home.setMessage(message);
		return home;
	}
	
	@RequestMapping("/{name}")
	public Home home(@PathVariable String name) {
		Home home = homeMapper.readHome(name);
		return home;
	}
}