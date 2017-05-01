package kr.doo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.doo.domain.MyDomain;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyController {
	
	private static ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value="/hello", produces="application/json; charset=utf8")
	public @ResponseBody MyDomain hello(@RequestBody MyDomain domain) throws IOException {
		
		System.out.println(domain);
		log.debug(domain.toString());
		
		File dataFile = new File("D:/git/MyBoot/target/test-classes/data/vinc.json");
		MyDomain response = mapper.readValue(dataFile, MyDomain.class);
		
		return response;
	}
	
	@RequestMapping(value="/anyoung", produces="application/json; charset=euckr")
	public @ResponseBody MyDomain anyoung(@RequestBody MyDomain domain) throws IOException {
		
		System.out.println(domain);
		log.debug(domain.toString());
		
		File dataFile = new File("D:/git/MyBoot/target/test-classes/data/mins.json");
		MyDomain response = mapper.readValue(dataFile, MyDomain.class);
		
		return response;
	}
	
	@RequestMapping(value="api")
	public String api(@RequestBody String json,
			@RequestHeader(value="Content-Type") String contentType,
			@RequestHeader(value="X-FORWARDED-FOR") String clientIP
			) throws JsonProcessingException {

		System.out.printf("%s, %s, api=%s\n", clientIP, contentType, json);
		
		Map<String, Object> map = new HashMap<String, Object>();
		String jsonString = mapper.writeValueAsString(map);
		return jsonString;
	}
	
}
