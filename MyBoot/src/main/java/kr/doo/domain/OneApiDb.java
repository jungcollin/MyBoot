package kr.doo.domain;

import lombok.Data;

@Data
public class OneApiDb<T> {
	
	private String crtDt;
	private String crtNo;

	private T data;
}
