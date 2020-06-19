package com.oracle.service;

import java.util.List;

import com.oracle.domain.Fenlei;
import com.oracle.domain.Lend;
import com.oracle.domain.PageBean;

public interface LendService {

	int addlend(Lend lend);
	 List<Fenlei> findfl();

	


		PageBean<Lend> showPesgefl(int pageNew, int pageSize);

		int[] delete(String[] ids);//删除

	 

		Lend dancha(int id1);//单查图书

		List<Lend> showLend();//多查

		List<Lend> showIdsLend(String[] ids);
		int update2(String back_date, String name);

		

	 
}
