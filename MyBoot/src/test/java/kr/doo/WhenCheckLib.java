package kr.doo;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

import kr.doo.domain.MyDomain;
import kr.doo.domain.OneApiReceive;
import kr.doo.domain.OneApiSend;
import kr.doo.domain.OneApiSendHeader;

@SuppressWarnings("serial")
public class WhenCheckLib {

	
	private static final ObjectMapper parseMapper = new ObjectMapper();
	
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// UPPER_CASE_STRATEGY
		mapper.setPropertyNamingStrategy(new SnakeCaseStrategy() {
			@Override
			public String translate(String input) {
				String snakeCase = super.translate(input);
				return snakeCase.toUpperCase();
			}
		});
	}
	
	@Test
	public void jacksonStrategy() throws IOException {
		
		
		File dataFile = new ClassPathResource("data/mins.json").getFile();
		MyDomain domain = parseMapper.readValue(dataFile, MyDomain.class);
		
//		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain));
		
		String receive = send(domain);
		
		
		
		@SuppressWarnings("unchecked")
		OneApiReceive<MyDomain> rcv = mapper.readValue(receive, OneApiReceive.class);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rcv));
		
	}
	
	private <T> String send(T body) throws IOException {
		
		File headFile = new ClassPathResource("data/sendHead.json").getFile();
		OneApiSendHeader head = parseMapper.readValue(headFile, OneApiSendHeader.class);
		
		OneApiSend<T> send = new OneApiSend<T>();
		send.setHeader(head);
		send.setBody(body);
		
		String reqJson = mapper.writeValueAsString(send);
		
		// restTemplate
		
		
		File dataFile = new ClassPathResource("data/receive.json").getFile();
		@SuppressWarnings("unchecked")
		OneApiReceive<MyDomain> rcv = parseMapper.readValue(dataFile, OneApiReceive.class);
		
		String resJson = mapper.writeValueAsString(rcv);
		System.out.println(resJson);
		
		return resJson;
	}
}


class Vo {
	
	public Vo(String a, String b) {
		this.aBcD = a;
		this.aaaBddEREB = b;
	}
	
	String aBcD;
	String aaaBddEREB;
	public String getaBcD() {
		return aBcD;
	}
	public void setaBcD(String aBcD) {
		this.aBcD = aBcD;
	}
	public String getAaaBddEREB() {
		return aaaBddEREB;
	}
	public void setAaaBddEREB(String aaaBddEREB) {
		this.aaaBddEREB = aaaBddEREB;
	}
}