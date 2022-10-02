package com.TMS.dao;
import java.util.List;

import com.TMS.bean.*;

import com.TMS.exception.AdminException;
import com.TMS.exception.BidderException;

public interface BidderDao {

	public String acceptBid(String bid, String tid,String vid)throws AdminException;
	
	public String rejectBid(String bid)throws AdminException;
	
	public String bidTendor(String bid,String vid , String tid , int bamount,String deadline)throws AdminException;
	
	public List<Bidderbean> getAllBidsOnTendor(String tid)throws AdminException;
	
	public List<Bidderbean> getAllBidsOfVendor(String vid)throws AdminException;
	
}
