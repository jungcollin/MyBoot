package kr.doo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

import kr.doo.domain.MyDomain;
import kr.doo.domain.OneApiReceive;
import kr.doo.domain.OneApiReceiveHeader;
import kr.doo.domain.OneApiSend;
import kr.doo.domain.OneApiSendHeader;

@SuppressWarnings("serial")
public class WhenCheckLib {

	private static final ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(new SnakeCaseStrategy() {
			@Override
			public String translate(String input) {
				if("header".equals(input) || "body".equals(input)) {
					return input;
				}else {
					String snakeCase = super.translate(input);
					return snakeCase.toUpperCase();
				}
			}
		});
	
	@Test
	public void jacksonStrategy() throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		
		File dataFile = new ClassPathResource("data/mins.json").getFile();
		MyDomain domain = mapper.readValue(dataFile, MyDomain.class);
		
//		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain));
		
		String receive = send(domain);
		
		OneApiReceive<MyDomain> rcv = mapper.readValue(receive, new TypeReference<OneApiReceive<MyDomain>>(){});
		
		System.out.printf("response=> %s\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rcv));
		OneApiReceiveHeader rcvHead = rcv.getHeader();
		System.out.printf("responseHeader=> %s\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rcvHead));
		
		
		MyDomain rcvBody = rcv.getBody();
		System.out.printf("responseBody=> %s\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rcvBody));
		
	}
	
	private <T> String send(T body) throws IOException {
		
		File headFile = new ClassPathResource("data/sendHead.json").getFile();
		OneApiSendHeader head = mapper.readValue(headFile, OneApiSendHeader.class);
		
		OneApiSend<T> send = new OneApiSend<T>();
		send.setHeader(head);
		send.setBody(body);
		
		System.out.printf("request=> %s\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(send));
		
		// restTemplate
		
		
		File dataFile = new ClassPathResource("data/receive.json").getFile();
		OneApiReceive<?> rcv = mapper.readValue(dataFile, OneApiReceive.class);
		
		String resJson = mapper.writeValueAsString(rcv);
		
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