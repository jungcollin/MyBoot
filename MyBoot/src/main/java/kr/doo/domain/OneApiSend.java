package kr.doo.domain;

import lombok.Data;

@Data
public class OneApiSend<T> {

	private OneApiSendHeader header;
	
	private T body;
}
