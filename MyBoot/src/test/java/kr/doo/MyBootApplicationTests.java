package kr.doo;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.doo.domain.MyDomain;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBootApplicationTests {
	
	RestTemplate restTemplate = new RestTemplate();
	
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void hello() throws IOException {
		
		File dataFile = new ClassPathResource("data/mins.json").getFile();
		MyDomain request = mapper.readValue(dataFile, MyDomain.class);
		
		System.out.println(request);
		
		MyDomain response = restTemplate.postForObject("http://localhost/hello", request, MyDomain.class);
		
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		
		System.out.println(jsonString);
	}
	
}
