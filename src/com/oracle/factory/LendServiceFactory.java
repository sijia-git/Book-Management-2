package com.oracle.factory;

import com.oracle.service.LendService;
import com.oracle.service.impl.LendServiceImpl;

public class LendServiceFactory {

	public static LendService getLendServiceImpl() {
		// TODO Auto-generated method stub
		return new LendServiceImpl();
	}
	
}

