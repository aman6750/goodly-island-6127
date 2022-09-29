package com.TMS.bean;

public class Tender_statusbean {

	
	private String tid;
	private String bid;
	private String status;
	private String vid;
	
	
	public Tender_statusbean(String tid, String bid, String status, String vid) {
		super();
		this.tid = tid;
		this.bid = bid;
		this.status = status;
		this.vid = vid;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getVid() {
		return vid;
	}


	public void setVid(String vid) {
		this.vid = vid;
	}


	@Override
	public String toString() {
		return "Tender_statusbean [tid=" + tid + ", bid=" + bid + ", status=" + status + ", vid=" + vid + "]";
	}
	
	public Tender_statusbean() {
		// TODO Auto-generated constructor stub
	}
	
	
}
