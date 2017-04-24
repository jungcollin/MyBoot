package kr.doo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.doo.domain.MyDomain;

@RestController
public class MyController {
	
	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/hello")
	public @ResponseBody MyDomain hello(@RequestBody MyDomain domain) throws IOException {
		
		File dataFile = new File("D:/workspace/MyBoot/src/test/resources/data/vinc.json");
		MyDomain response = mapper.readValue(dataFile, MyDomain.class);
		
		return response;
	}
	
}
