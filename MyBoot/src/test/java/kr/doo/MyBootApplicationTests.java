package kr.doo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.doo.bind.UpperSnakeCaseStrategy;
import kr.doo.domain.MyDomain;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBootApplicationTests {
	
	@Autowired private WebApplicationContext wac;
	MockMvc mvc;
	
	RestTemplate restTemplate = new RestTemplate();
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	

	@Test
	public void hello() throws IOException {
		
		File dataFile = new ClassPathResource("data/vinc.json").getFile();
		MyDomain request = mapper.readValue(dataFile, MyDomain.class);
		
		System.out.println(request);
		
		MyDomain response = restTemplate.postForObject("http://localhost/hello", request, MyDomain.class);
		
		UpperSnakeCaseStrategy s = new UpperSnakeCaseStrategy();
		mapper.setPropertyNamingStrategy(s);
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		
		System.out.println(jsonString);
	}
	
//	@Test
	public void anyoung() throws IOException {
		
		File dataFile = new ClassPathResource("data/vinc.json").getFile();
		MyDomain request = mapper.readValue(dataFile, MyDomain.class);
		
		System.out.println(request);
		
		MyDomain response = restTemplate.postForObject("http://localhost/anyoung", request, MyDomain.class);
		
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		
		System.out.println(jsonString);
	}
	
//	@Test
	public void api() throws IOException {
		
		File dataFile = new ClassPathResource("data/vinc.json").getFile();
		MyDomain request = mapper.readValue(dataFile, MyDomain.class);
		
		System.out.println(request);
		
		MyDomain response = restTemplate.postForObject("http://localhost/api", request, MyDomain.class);
		
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		
		System.out.println(jsonString);
	}
	
//	@Test
	public void client() throws ClientProtocolException, IOException {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("레스트풀 URL");

		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("userid", "한글로테스트"));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
		formEntity.setContentType("application/json");
		formEntity.setContentEncoding("UTF-8");

		request.setEntity(formEntity);

		HttpResponse response = httpClient.execute(new HttpGet("http://localhost/utf8"));
		HttpEntity entity = response.getEntity();
		String jsonData = EntityUtils.toString(entity);
		
		System.out.println(jsonData);
	}
	
//	@Test
	public void mvcTest() throws JsonProcessingException, Exception {
		MyDomain request = new MyDomain();
		request.setName("나야나");
		request.setPrice(11);
		
		mvc.perform(post("/hello")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//			.andExpect(jsonPath("$.name", is(request.getName().toUpperCase())))
			;
		
	}
}
