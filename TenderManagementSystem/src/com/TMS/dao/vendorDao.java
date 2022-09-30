package com.TMS.dao;
import java.util.List;

import com.TMS.bean.*;
import com.TMS.exception.VendorException;

public interface vendorDao {

	public String registernewVendor(Vendorbean vendor);
	
	public List<Vendorbean> viewAllVendors()throws VendorException;
	
	
	
}
