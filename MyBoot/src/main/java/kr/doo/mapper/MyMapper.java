package kr.doo.mapper;

import org.springframework.stereotype.Component;

@Component
public class MyMapper {

	private int no = 0;
	
	public int findMyData() {
		
		return no++;
	}
	
	public int updatePending() {
		
		return 1;
	}
}
