package kr.doo.domain;

import lombok.Data;

@Data
public class OneApiReceive<T> {

	private OneApiReceiveHeader header;
	
	private T body;
}
