package com.oracle.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.dao.BookDao;
import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.domain.User;
import com.oracle.util.DBUtilsPlus;
/**
 * @Descripthion 操作数据库CURD
 * @author HBB
 *
 */
public class BookDaoImpl implements BookDao {

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
	public int yanzheng(String name,String flname) {
		// TODO Auto-generated method stub
		int t = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select * from book where name=? and flname=? ");
			ps.setString(1, name);
			ps.setString(2, flname);
			rs = ps.executeQuery();
			if (rs.next()) {
				t = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public int addbook(Book book) {//1.增加图书
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"insert into book (flname,name,money,press,state,reader) value(?,?,?,?,?,?)");
			ps.setString(1, book.getFlname());
			ps.setString(2, book.getName());
			ps.setString(3, book.getMoney());
			ps.setString(4, book.getPress());
			ps.setString(5, book.getState());
			ps.setString(6, book.getReader());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;

	}

	@Override
	public PageBean<Book> showPesgefl(int pageNew, int pageSize) {//分页显示图书列表
		
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPageNew(pageNew);
		pb.setPageSize(pageSize);
		pb.setCounts(this.Count());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from book order by id desc limit ?,?";
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
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				Book b = new Book(id, flname, name, money, press, state, reader);

				list.add(b);
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
	public int Count() {//查询总记录数
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select count(*) from book ";
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
				ps = conn.prepareStatement("delete from book where id=?");
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
	public Book dancha(int id1) {//3.根据图书id查询图书信息
		// TODO Auto-generated method stub
		Book b = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();//获取数据库连接
			ps = conn.prepareStatement("select * from book where id=? ");//创建ps对象
			ps.setInt(1, id1);//几个问号设置几个
			rs = ps.executeQuery();//执行查询语句，并返回查询结果rs对象
			if (rs.next()) {//存在图书，封装返回
				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				b = new Book(id, flname, name, money, press, state, reader);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;//返回对象
	}
	
	@Override
	public int update2(Book book) {//4.修改图书
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"update book set flname=?,name=?,money=?,press=?,state=?,reader=?  where id=? ");
			ps.setString(1, book.getFlname());
			ps.setString(2, book.getName());
			ps.setString(3, book.getMoney());
			ps.setString(4, book.getPress());
			ps.setString(5, book.getState());
			ps.setString(6, book.getReader());
			ps.setInt(7, book.getId());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public List<Book> showBook() {//5.全查图书
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
    	List<Book> list=new ArrayList<Book>();//创建用于存放图书的list集合
		try {
			conn = DBUtilsPlus.getConnection();// 获得数据库连接
			String sql = "select * from book order by id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				Book b = new Book(id, flname, name, money, press, state, reader);

				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Book> showIdsBook(String[] ids) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtilsPlus.getConnection();
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
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				Book b = new Book(id, flname, name, money, press, state, reader);

				list.add(b);
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

	@Override
	public int jieshu(Book book) {//用户前台借书
		// TODO Auto-generated method stub
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"update book set  state=?,reader=?  where id=? ");
			ps.setString(1,"借出");
			ps.setString(2,book.getReader());
			ps.setInt(3,book.getId());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	@Override
	public int huanshu(Book book) {//用户前台还书
		// TODO Auto-generated method stub
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"update book set  state=?,reader=?  where id=? ");
			ps.setString(1,"未借出");
			ps.setString(2,book.getReader());
			ps.setInt(3,book.getId());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public PageBean<Book> showPesgefind(Book where, int pageNew, int pageSize) {//高级搜索
		// TODO Auto-generated method stub
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPageNew(pageNew);
		pb.setPageSize(pageSize);
		pb.setCounts(this.Count2(where));
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtilsPlus.getConnection();//动态生成一个sql
			StringBuilder sql = new StringBuilder("select * from book where 1=1");//1=1,防止查询条件为空时语句报错
			List<String> params=new ArrayList<String>();//参数列表
			
			//从where中取出条件
			if(where.getFlname()!=null && !where.getFlname().trim().isEmpty()){//分类名不为null，且去掉左右空格后不为空
				sql.append(" and flname like ?");//拼接字符串
				params.add("%"+where.getFlname()+"%");//拼接参数
			}
			if(where.getName()!=null &&!where.getName().trim().isEmpty()){//模糊查询
				sql.append(" and name like ?");
				params.add("%"+where.getName()+"%");//把名字给参数，模糊查询需要%
			}
			if(where.getPress()!=null &&!where.getPress().trim().isEmpty()){
				sql.append(" and press like ?");
				params.add("%"+where.getPress()+"%");
			}
			if(where.getState()!=null &&!where.getState().trim().isEmpty()){
				sql.append(" and state like ?");
				params.add("%"+where.getState()+"%");
			}
			if(where.getReader()!=null && !where.getReader().trim().isEmpty()){
				sql.append(" and reader like ?");
				params.add("%"+where.getReader()+"%");
			}
			sql.append(" order by id desc limit ?,?");	//从？位置开始，取？条记录
			
			
			ps = conn.prepareStatement(sql.toString());
			System.out.println(sql.toString());//打印sql语句
			int i=0;
			 for(int j=0;j<params.size();j++){
					
					ps.setString(j+1, params.get(j));//把问号换成参数
					i++;
				}
			ps.setInt(i+1, (pageNew - 1) * 5);
			ps.setInt(i+2, pageSize);
			rs = ps.executeQuery();//执行sql语句
			while (rs.next()) {

				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				Book b = new Book(id, flname, name, money, press, state, reader);

				list.add(b);//从数据库中取出的数据封装成list传送给页面
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

	private int Count2(Book where) {//高级搜索
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = DBUtilsPlus.getConnection();
			///动态生成sql语句，stringbuilder
			StringBuilder sql =new StringBuilder("select count(*) from book  where 1=1");//得到记录数
			List<String> params=new ArrayList<String>();//参数列表
			if(where.getFlname()!=null && !where.getFlname().trim().isEmpty()){//分类名不是null且分类名去掉左右的空格后不为空
				sql.append(" and flname like ?");
				params.add("%"+where.getFlname()+"%");
			}
			if(where.getName()!=null &&!where.getName().trim().isEmpty()){//书名
				sql.append(" and name like ?");
				params.add("%"+where.getName()+"%");
			}
			if(where.getPress()!=null &&!where.getPress().trim().isEmpty()){//出版社
				sql.append(" and press like ?");
				params.add("%"+where.getPress()+"%");
			}
			if(where.getState()!=null &&!where.getState().trim().isEmpty()){//状态
			//	sql.append(" and state like ?");//like:模糊查询
				sql.append(" and state = ?");//状态不需要模糊查询
				params.add("%"+where.getState()+"%");
			}
			if(where.getReader()!=null && !where.getReader().trim().isEmpty()){//读者
				sql.append(" and reader like ?");
				params.add("%"+where.getReader()+"%");
			}
			
			ps = conn.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			//执行之前，把问号设置成参数
             for(int j=0;j<params.size();j++){
				
				ps.setString(j+1, params.get(j));
			}
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

	
	
}
