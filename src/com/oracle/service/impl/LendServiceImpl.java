package com.oracle.service.impl;

import java.util.List;

import com.oracle.dao.LendDao;
import com.oracle.dao.impl.LendDaoImpl;
import com.oracle.domain.Fenlei;
import com.oracle.domain.Lend;
import com.oracle.domain.PageBean;
import com.oracle.service.LendService;



public  class LendServiceImpl implements LendService{
	
	//LendDao ld=new LendDaoImpl();
	LendDao ld = new LendDaoImpl();

	public List<Fenlei> findfl() {

		// TODO Auto-generated method stub
		return ld.findfl();
	}

	
	@Override
	public int addlend(Lend lend) {//增加
		// TODO Auto-generated method stub
		return ld.addlend(lend);
	}

	@Override
	public PageBean<Lend> showPesgefl(int pageNew, int pageSize) {
		// TODO Auto-generated method stub
		return ld.showPesgefl(pageNew,pageSize);
	}

	@Override
	public int[] delete(String[] ids) {//删除
		// TODO Auto-generated method stub
		return ld.delete(ids);
	}

	@Override
	public Lend dancha(int id1) {//单查
		// TODO Auto-generated method stub
		return ld.dancha(id1);
	}

	@Override
	public int update2(String back_date, String name) {//改
		// TODO Auto-generated method stub
		return ld.update2(back_date,name);
	}

	@Override
	public List<Lend> showLend() {//多查
		// TODO Auto-generated method stub
		return ld.showLend();
	}


	@Override
	public List<Lend> showIdsLend(String[] ids) {
		// TODO Auto-generated method stub
		return ld.showIdsLend(ids);
	}



	


	

	 

	 
	 
		
}
