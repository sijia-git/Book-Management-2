package com.oracle.domain;

import java.io.Serializable;

public class Lend implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String flname;
	private String  name;
	private String money;
	private String press;
	private String  reader;
	private String lend_date;
	private String back_date;
	public Lend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lend(int id, String flname, String name, String money, String press, String reader,String lend_date,String back_date) {
		super();
		this.id = id;
		this.flname = flname;
		this.name = name;
		this.money = money;
		this.press = press;
		this.reader = reader;
		this.lend_date = lend_date;
		this.back_date = back_date;
	}
	public Lend(String flname, String name, String money, String press, String reader,String lend_date,String back_date) {
		super();
		this.flname = flname;
		this.name = name;
		this.money = money;
		this.press = press;
		this.reader = reader;
		this.lend_date = lend_date;
		this.back_date = back_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlname() {
		return flname;
	}
	public void setFlname(String flname) {
		this.flname = flname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	
	public String getLend_date() {
		return lend_date;
	}
	public void setLend_date(String lend_date) {
		this.lend_date = lend_date;
	}
	public String getBack_date() {
		return back_date;
	}
	public void setBack_date(String back_date) {
		this.back_date = back_date;
	}
	@Override
	public String toString() {
		return "Lend [id=" + id + ", flname=" + flname + ", name=" + name
				+ ", money=" + money + ", press=" + press + ", reader="
				+ reader + ", lend_date=" + lend_date + ", back_date="
				+ back_date + "]";
	}
	
	
	 
 
	
}
