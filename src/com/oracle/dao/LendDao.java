package com.oracle.dao;

import java.util.List;


import com.oracle.domain.Fenlei;
import com.oracle.domain.Lend;
import com.oracle.domain.PageBean;

public interface LendDao {

	 List<Fenlei> findfl();


	int addlend(Lend lend);
   

	int Count();

	PageBean<Lend> showPesgefl(int pageNew, int pageSize);

	int[] delete(String[] ids);

	Lend  dancha(int id1);


	List<Lend> showLend();

	List<Lend> showIdsLend(String[] ids);


	int update2(String back_date, String name);



	
 
}
