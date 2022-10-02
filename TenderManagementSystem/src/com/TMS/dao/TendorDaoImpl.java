package com.TMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.TMS.bean.Tender_statusbean;
import com.TMS.bean.tenderbean;
import com.TMS.exception.AdminException;
import com.TMS.utility.DBUtil;

public class TendorDaoImpl implements TendorDao {

	@Override
	public String CreatenewTendor(tenderbean tendor) {
		
		String message = "Tender Addition Failed!";
		
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("insert into tendor values(?,?,?,?,?,?,?)");
			ps.setString(1, tendor.getTid());
			ps.setString(2, tendor.getTname());
			ps.setString(3, tendor.getTtype());
			ps.setInt(4, tendor.getTprice());
			ps.setString(5, tendor.getTdescription());
			
//			Date deadline = tendor.getTdeadline();
//			java.sql.Date sdeadline = new java.sql.Date(deadline.getTime());
//			ps.setDate(6, sdeadline);
			ps.setString(6, tendor.getTdeadline());
			
			ps.setString(7, tendor.getTlocation());
			
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				message = "Tendor created succesfully  Your Tender id: " + tendor.getTid();
			}
			
		} catch (SQLException e) {
			
			// TODO: handle exception
			e.printStackTrace();
			
			message = e.getMessage();
			
		}
		
		
		
		return message;
		
	}
	
	
	

	@Override
	public List<tenderbean> getTenderDetailsbyId(String id) throws AdminException {
		
		List<tenderbean> list = new ArrayList<>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("select * from tendor where tid = ?");
			
			ps.setString(1, id);
			
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				
				String tid=rs.getString("tid");
				String tname=rs.getString("tname");
				String ttype=rs.getString("ttype");
				int tprice=rs.getInt("tprice");
				String tdescription=rs.getString("tdescription");
				String tdeadline=rs.getString("tdeadline");
				String tlocation=rs.getString("tlocation");
				
				tenderbean tb = new tenderbean(tid,tname,ttype,tprice,tdescription,tdeadline,tlocation);
				
				list.add(tb);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
			
		}
		
		
		return list;
		
	}
	
	@Override
	public List<tenderbean> getAllTenders() throws AdminException{
		List<tenderbean> tenderList  = new ArrayList<tenderbean>();
		
	
		
		try (Connection conn =DBUtil.provideConnection()){
			    PreparedStatement ps =conn.prepareStatement("select * from tendor");
			
			ResultSet rs =ps.executeQuery();
			while(rs.next())
			{
				tenderbean tender=new tenderbean();
				
				tender.setTid("tid");
				tender.setTname(rs.getString("tname"));
				tender.setTtype(rs.getString("ttype"));
				tender.setTprice(rs.getInt("tprice"));
				tender.setTdescription(rs.getString("tdescription"));
//				java.util.Date udate = new java.util.Date(rs.getDate(6).getTime());
//				tender.setDeadline(udate);
				tender.setTdeadline("tdeadline");
				tender.setTlocation(rs.getString("tlocation"));
				
				
				tenderList.add(tender);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
			throw new AdminException(e.getMessage());
			
		}
		
		
		
		return tenderList;
	}
	
	
	
	@Override
	public String getTenderStatus(String tenderId)throws AdminException {
		
		String status = "Not Assigned";
		

		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from tender_status where tid=?");
			
			ps.setString(1, tenderId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				//Tender Has been Assigned 
				
				status = "Assigned";
			}
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			throw new AdminException(e.getMessage());
		}
		
		return status;
	}

	@Override
	public String assignTender(String tenderId, String vendorId,String bidderId)throws AdminException {
		
		String status = "Tender Assigning failed";
		
		
	
		try (Connection con = DBUtil.provideConnection()){
			PreparedStatement  ps1 = con.prepareStatement("select * from tender_status where tid=?");
			ps1.setString(1, tenderId);
			
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()){
				
				status = "Tender is Already Assigned to Vendor: "+rs.getString("vid");
			}
			else{
				
				PreparedStatement ps = con.prepareStatement("insert into tender_status values(?,?,?,?)");
				
				ps.setString(1,tenderId);
				ps.setString(2, bidderId);
				ps.setString(3, "Assigned");
				ps.setString(4, vendorId);

				int k = ps.executeUpdate();
				if(k>0){
					status = "Tender: "+tenderId+" has been Assigned to vendor: "+vendorId;
				}
				
			}
		} catch (SQLException e) {
			status = status + e.getMessage();
			throw new AdminException(e.getMessage());
		}
		
		
		return status;
	}

	@Override
	public List<Tender_statusbean> getAllAssignedTenders() throws AdminException{
		
		List<Tender_statusbean> statusList = new ArrayList<Tender_statusbean>();
		
	
		try(Connection con = DBUtil.provideConnection()) {
		
			PreparedStatement ps = con.prepareStatement("select * from tender_status");
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Tender_statusbean status = new Tender_statusbean(rs.getString("tid"),rs.getString("bid"),rs.getString("status"),rs.getString("vid"));
				
				statusList.add(status);
			}
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			
			
			throw new AdminException(e.getMessage());
		}
		
		
		
		return statusList;
	}




	@Override
	public boolean removeTendor(String tid) throws AdminException {
		
		boolean flag = false ;
		
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("delete from tendor where tid = ?");
			ps.setString(1, tid);
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				flag = true;
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		return flag;
		
	}
	
	
	@Override
	public String updateTender(tenderbean tender)throws AdminException {
		String status = "Tender Updation Failed!";
		
		
		
		
		
		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement pst = con.prepareStatement("UPDATE tendor SET tname=?,ttype=?,tprice=?,tdescription=?,tdeadline=?,tlocation=? where tid=?");
			
			pst.setString(1, tender.getTname());
			pst.setString(2, tender.getTtype());
			pst.setInt(3, tender.getTprice());
			pst.setString(4, tender.getTdescription());
			
//			Date deadline = tender.getTdeadline();
//			java.sql.Date sDeadline = new java.sql.Date(deadline.getTime());
//			pst.setDate(5, sDeadline);
			
			pst.setString(5,tender.getTdeadline());
			
			
			pst.setString(6, tender.getTlocation());
			pst.setString(7, tender.getTid());
			int x=pst.executeUpdate();
			if(x>0)
				status="TENDER DETAILS UPDATED SUCCSESFULLY";
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	
	
	
}
