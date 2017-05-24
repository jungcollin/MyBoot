package kr.doo.service;

import kr.doo.exception.MyRuntimeException;

public abstract class AbstractService {

	public void call() throws MyRuntimeException {
		try {
			this.process();
		}finally {
			this.business();
		}
	}

	protected abstract void process();
	
	protected abstract void business();
}
