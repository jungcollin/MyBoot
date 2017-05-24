package kr.doo.service;

import kr.doo.exception.MyRuntimeException;

public class YourService extends AbstractService {
	
	@Override
	protected void process() {
		try {
			// 조회
			// 펜딩
			// 전송
			// 완료
		}catch(Exception e) {
			throw new MyRuntimeException();
		}finally {
			// make business parameters
		}
	}

	@Override
	protected void business() {
		// sp call
		String result = null;
		
		if("00000".equals(result)) {
			throw new MyRuntimeException();
		}
	}

}
