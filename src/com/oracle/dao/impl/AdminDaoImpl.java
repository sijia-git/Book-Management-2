package com.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.dao.AdminDao;
import com.oracle.domain.Admin;
import com.oracle.util.DBUtilsPlus;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(Admin a) {//登录
	    Admin admin=null;//声明admin
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//3.查询数据库
		try {
			conn = DBUtilsPlus.getConnection();//获取链接
			ps = conn.prepareStatement("select username,password from admin where username=?");
			ps.setString(1, a.getUsername());//通过username查询
            rs=ps.executeQuery();
            if(rs.next()){//真，用户存在
				String userName=rs.getString(1);//拿到用户名，第1列
				String password=rs.getString(2);//拿到密码，第2列
                admin=new Admin();//把应户名和密码封装到admin里
                admin.setUsername(userName);
                admin.setPassword(password);
        	   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);//关闭资源
		}
		return admin;//返回admin
	}

	@Override
	public int addAdmin(Admin a) { //添加用户
		// TODO Auto-generated method stub
	    int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("insert into admin (name,username,password,phone,touxiang) values(?,?,?,?,?)");

			ps.setString(1, a.getName());
			ps.setString(2, a.getUsername());
			ps.setString(3, a.getPassword());
			ps.setString(4, a.getPhone());
			ps.setString(5, a.getTouxiang());
			s = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}
		
		return s;
	}
	
	/**
	 * @Description 显示管理员信息
	 */
	@Override
	public Admin showAdmin(String un) {//显示管理员信息
		// TODO Auto-generated method stub
		Admin s=new Admin();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select * from admin where username=?");
			ps.setString(1, un);
			rs = ps.executeQuery();
			if(rs.next()) {	//存在用户                                                                                                             			
				int id = rs.getInt(1);
                String name = rs.getString(2);
				String username= rs.getString(3);
				String password = rs.getString(4);
				String phone = rs.getString(5);
				String touxiang = rs.getString(6);
                s=new Admin(id, name, username, password, phone,touxiang);	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}
		return s;
	}

	@Override
	public int yanzheng(String username) {//验证用户
		int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select username from admin where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {				
			 s=1; 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return s;
	}

	@Override
	public int updatemm(String newpassword, String un) {//修改密码
		// TODO Auto-generated method stub
		int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update admin set password=?  where username=? ");
			ps.setString(1, newpassword);
			ps.setString(2, un);
			s= ps.executeUpdate();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}

		return s;
	}


}
