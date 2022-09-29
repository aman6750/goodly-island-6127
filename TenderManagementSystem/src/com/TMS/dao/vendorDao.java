package com.TMS.dao;
import java.util.List;

import com.TMS.bean.*;

public interface vendorDao {

	public String registernewVendor(Vendorbean vendor);
	
	public List<Vendorbean> viewAllVendor();
	
}
