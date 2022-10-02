package com.TMS.bean;

import java.sql.Date;


public class Bidderbean {

	private String bid;
	private String vid;
	private String tid;
	private int bidamount;
	private String deadline;
	private String status;
	
	
	
	
	public Bidderbean(String bid, String vid, String tid, int bidamount, String deadline, String status) {
		super();
		this.bid = bid;
		this.vid = vid;
		this.tid = tid;
		this.bidamount = bidamount;
		this.deadline = deadline;
		this.status = status;
	}




	public String getBid() {
		return bid;
	}




	public void setBid(String bid) {
		this.bid = bid;
	}




	public String getVid() {
		return vid;
	}




	public void setVid(String vid) {
		this.vid = vid;
	}




	public String getTid() {
		return tid;
	}




	public void setTid(String tid) {
		this.tid = tid;
	}




	public int getBidamount() {
		return bidamount;
	}




	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}




	public String getDeadline() {
		return deadline;
	}




	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Bidderbean [bid=" + bid + ", vid=" + vid + ", tid=" + tid + ", bidamount=" + bidamount + ", deadline="
				+ deadline + ", status=" + status + "]";
	}
	
	
    public Bidderbean() {
		// TODO Auto-generated constructor stub
	}
	
	
}
