package kr.doo.domain;

import lombok.Data;

@Data
public class OneApiSendHeader {

	private String apiVer;
	
	private String apiKey;
	
	private String trId;
}
