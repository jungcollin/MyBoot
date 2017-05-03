package kr.doo.domain;

import lombok.Data;

@Data
public class OneApiReceiveHeader {

	private String resultCode;
	
	private String resultMsg;
	
	private String trId;
}
