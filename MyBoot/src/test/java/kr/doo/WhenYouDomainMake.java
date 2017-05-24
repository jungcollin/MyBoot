package kr.doo;

import org.junit.Test;

import kr.doo.domain.MyDomain;
import kr.doo.domain.OneApiDb;

public class WhenYouDomainMake {

	@Test
	public void subdata() {
		
		MyDomain m = new MyDomain();
		m.setName("lawsn");
		m.setPrice(5009);
		
		OneApiDb<MyDomain> db = new OneApiDb<MyDomain>();
		db.setData(m);
		
	}
}
