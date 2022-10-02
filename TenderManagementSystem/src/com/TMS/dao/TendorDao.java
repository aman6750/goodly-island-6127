package com.TMS.dao;

import java.util.List;

import com.TMS.bean.Tender_statusbean;
import com.TMS.bean.tenderbean;
import com.TMS.exception.AdminException;

public interface TendorDao {

	
	public String CreatenewTendor(tenderbean tendor);
	
	public List<tenderbean> getTenderDetailsbyId( String id)throws AdminException;
	
	public List<tenderbean> getAllTenders()throws AdminException;
	
	public String getTenderStatus(String tenderId)throws AdminException ;
	
	public String assignTender(String tenderId, String vendorId,String bidderId)throws AdminException;
	
	public List<Tender_statusbean> getAllAssignedTenders() throws AdminException;
	

	public boolean removeTendor(String tid)throws AdminException;
	
	public String updateTender(tenderbean tender)throws AdminException;
	
}
