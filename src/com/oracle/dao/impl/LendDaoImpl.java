package com.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.dao.LendDao;
import com.oracle.domain.Fenlei;
import com.oracle.domain.Lend;
import com.oracle.domain.PageBean;
import com.oracle.util.DBUtilsPlus;

public  class LendDaoImpl implements LendDao {
	@Override
	public ArrayList<Fenlei> findfl() {//查找分类
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Fenlei> list = new ArrayList<Fenlei>();
		try {
			conn = DBUtilsPlus.getConnection();// 获得数据库连接
			String sql = "select * from fenlei order by id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				Fenlei s = new Fenlei(id, name);
				list.add(s);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return list;
	}
 
	
	@Override
	public int addlend(Lend lend) {//1.增加图书
		
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"insert into lend (flname,name,money,press,reader,lend_date,back_date) value(?,?,?,?,?,?,?)");
			ps.setString(1, lend.getFlname());
			ps.setString(2, lend.getName());
			ps.setString(3, lend.getMoney());
			ps.setString(4, lend.getPress());
			ps.setString(5, lend.getReader());
			ps.setString(6, lend.getLend_date());
			ps.setString(7, lend.getBack_date());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;

	}

	@Override
	public PageBean<Lend> showPesgefl(int pageNew, int pageSize) {//分页显示图书列表
		PageBean<Lend> pb = new PageBean<Lend>();
		pb.setPageNew(pageNew);
		pb.setPageSize(pageSize);
		pb.setCounts(this.Count());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Lend> list = new ArrayList<Lend>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from lend order by id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNew - 1) * 5);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String reader = rs.getString(6);
				String lend_date=rs.getString(7);
				String back_date=rs.getString(8);
				Lend l=new Lend(id,flname, name, money, press, reader,lend_date,back_date);

				list.add(l);
			}
			pb.setBeanList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return pb;
	}

	@Override
	public int Count() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select count(*) from lend ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				i = rs.getInt(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return i;
	}

	@Override
	public int[] delete(String[] ids) {//2.根据图书id删除图书信息
		// TODO Auto-generated method stub
		int[] n = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			  conn = DBUtilsPlus.getConnection();
			  conn.setAutoCommit(false);
			for (int i = 0; i < ids.length; i++) {
				// conn = DBUtilsPlus.getConnection();
				ps = conn.prepareStatement("delete from lend where id=?");
				ps.setInt(1, Integer.parseInt(ids[i]));
				ps.addBatch();
				
				n = ps.executeBatch();
			}
			
			    conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			DBUtilsPlus.close(conn, ps);
		}
		return n;
	}

	@Override
	public Lend dancha(int id1) {//3.根据图书id查询图书信息
		// TODO Auto-generated method stub
		Lend l = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();//获取数据库连接
			ps = conn.prepareStatement("select * from lend where id=? ");//创建ps对象
			ps.setInt(1, id1);//几个问号设置几个
			rs = ps.executeQuery();//执行查询语句，并返回查询结果rs对象
			if (rs.next()) {//存在图书，封装返回
				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String reader = rs.getString(6);
				String lend_date=rs.getString(7);
				String back_date=rs.getString(8);
				// double money=Integer.parseInt(money1);
				l = new Lend(id,flname, name, money, press, reader,lend_date,back_date);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;//返回对象
	}
	@Override
	public int update2(String back_date, String name) {//修改密码
		// TODO Auto-generated method stub
		int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update lend set back_date=?  where name=? ");
			ps.setString(1, back_date);
			ps.setString(2, name);
			s= ps.executeUpdate();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}

		return s;
	}



	
	@Override
	public List<Lend> showLend() {//5.全查图书
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    List<Lend> list=new ArrayList<Lend>();//创建用于存放图书的list集合
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from lend order by id desc";// 获得数据库连接
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String reader = rs.getString(6);
				String lend_date=rs.getString(7);
				String back_date=rs.getString(8);
				// double money=Integer.parseInt(money1);
				Lend l = new Lend(id,flname, name, money, press, reader,lend_date,back_date);
				list.add(l);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;//返回图书列表
	}
	
	@Override
	public List<Lend> showIdsLend(String[] ids) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Lend> list = new ArrayList<Lend>();// 创建用于存放图书的ArrayList集合
		try {
			conn = DBUtilsPlus.getConnection();// 获得数据库连接
			String sql =ByIds(ids);
			ps = conn.prepareStatement(sql);
			for(int i=0;i<ids.length;i++){
				ps.setInt(i+1, Integer.parseInt(ids[i]));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String reader = rs.getString(6);
				String lend_date=rs.getString(7);
				String back_date=rs.getString(8);
				Lend l = new Lend(id,flname, name, money, press, reader,lend_date,back_date);

				list.add(l);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return list;
	}

	private String ByIds(String[] ids) {
		// TODO Auto-generated method stub
		StringBuilder str=new StringBuilder("select * from book where id in (") ;
		for(int i=0;i<ids.length;i++){
			
			str.append("?");
			if(i<ids.length-1){
				str.append(",");
			}
		}
		str.append(")");
		
		return str.toString();
	}


	

	
	

	


	
}
