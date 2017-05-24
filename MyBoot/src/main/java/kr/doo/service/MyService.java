package kr.doo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.doo.exception.MyRuntimeException;
import kr.doo.mapper.MyMapper;

@Service
public class MyService extends AbstractService {

	@Autowired MyMapper myMapper;

	@Override
	protected void process() {
		try {
			// 조회
			// 펜딩
			// 전송
			// 완료
		}catch(Exception e) {
			throw new MyRuntimeException();
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
