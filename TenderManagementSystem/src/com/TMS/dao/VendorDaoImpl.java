
package com.TMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TMS.bean.Vendorbean;

import com.TMS.exception.VendorException;
import com.TMS.utility.DBUtil;

public class VendorDaoImpl implements vendorDao{
	
	

	@Override
	public String registernewVendor(Vendorbean vendor) {
		
		String message = " Registered failed..!";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			
			PreparedStatement ps1=conn.prepareStatement("select * from vendor where vemail=?");
			ps1.setString(1, vendor.getVemail());
			ResultSet rs1 =ps1.executeQuery();
			
			if(rs1.next()) {
				message = "Registration Declined! Email Id already Registered";
			}else {
				
				try {
					PreparedStatement ps =conn.prepareStatement("insert into vendor values(?,?,?,?,?,?,?)");
					
					
					ps.setString(1, vendor.getVid());
					ps.setString(2, vendor.getPassword());
					ps.setString(3, vendor.getVname());
					ps.setString(4, vendor.getVmobile());
					ps.setString(5, vendor.getVemail());
					ps.setString(6, vendor.getVcompany());
					ps.setString(7, vendor.getVaddres());
					
					int x = ps.executeUpdate();
					
					if(x>0) {
						
						message = "Vendor registered sucessfully";
					}
					
				} catch (SQLException e) {
					// TODO: handle exception
					message= e.getMessage(); 
				}
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			message = e.getMessage();
		}
	
		return message;
		
		
	}

	@Override
	public List<Vendorbean> viewAllVendors()throws VendorException {
		
		List<Vendorbean> vendors = new ArrayList<>();
		
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("select * from vendor");
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				
				Vendorbean vb = new Vendorbean(rs.getString("vid"),rs.getString("password"), rs.getString("vname"), rs.getString("vmobile"), rs.getString("vemail"), rs.getString("vcompany"), rs.getString("vaddres"));
				
				
				
				vendors.add(vb);
				
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new VendorException(e.getMessage());
		}
		
		
		if(vendors.size()==0) {
			throw new VendorException("vendor does not exist");
		}
		
		
		return vendors;
		
	}



	
	
}
