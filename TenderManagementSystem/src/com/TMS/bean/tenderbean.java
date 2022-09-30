package com.TMS.bean;

import java.sql.Date;

public class tenderbean {

	private String tid;
	private String tname;
	private String ttype;
	private int tprice;
	private String tdescription;
	private String tdeadline;
	private String tlocation;
	
	
	public tenderbean(String tid, String tname, String ttype, int tprice, String tdescription, String tdeadline2,
			String tlocation) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ttype = ttype;
		this.tprice = tprice;
		this.tdescription = tdescription;
		this.tdeadline = tdeadline2;
		this.tlocation = tlocation;
	}
	
	public tenderbean() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "tenderbean [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", tprice=" + tprice
				+ ", tdescription=" + tdescription + ", tdeadline=" + tdeadline + ", tlocation=" + tlocation + "]";
	}

	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getTname() {
		return tname;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	public String getTtype() {
		return ttype;
	}


	public void setTtype(String ttype) {
		this.ttype = ttype;
	}


	public int getTprice() {
		return tprice;
	}


	public void setTprice(int tprice) {
		this.tprice = tprice;
	}


	public String getTdescription() {
		return tdescription;
	}


	public void setTdescription(String tdescription) {
		this.tdescription = tdescription;
	}


	public String getTdeadline() {
		return tdeadline;
	}


	public void setTdeadline(String tdeadline) {
		this.tdeadline = tdeadline;
	}


	public String getTlocation() {
		return tlocation;
	}


	public void setTlocation(String tlocation) {
		this.tlocation = tlocation;
	}
	
	
	
	
	
}
