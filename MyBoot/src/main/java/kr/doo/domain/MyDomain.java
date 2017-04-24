package kr.doo.domain;

import java.util.List;

import lombok.Data;

@Data
public class MyDomain {

	private String name;
	
	private int price;
	
	private List<SubDomain> subDomain;
}
