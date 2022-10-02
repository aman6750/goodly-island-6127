package com.TMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.TMS.bean.Bidderbean;
import com.TMS.exception.AdminException;
import com.TMS.exception.BidderException;
import com.TMS.utility.DBUtil;

public class BidderDaoImpl implements BidderDao {

	@Override
	public String acceptBid(String bid, String tid, String vid) throws AdminException {

		String message = "Bid Acceptance Failed..!";
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("select * from tender_status where tid = ?");
			ps.setString(1, tid);
			
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				message = "Project is already assigned..!";
			}else {
				PreparedStatement ps2 =conn.prepareStatement("Update Bidder set status=? where bid = ? AND status = ?");
				
				ps2.setString(1, "Assigned");
				ps2.setString(2, bid);
				ps2.setString(3, "Pending");

				int x =ps2.executeUpdate();
				
				if(x>0) {
					
					
					message= "Bid Has Been Accepted Successfully!";
					TendorDao dao = new TendorDaoImpl();
					message = message + dao.assignTender(tid, vid, bid);
				}

				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		
		
		
		
		return message;
		
	}

	@Override
	public String rejectBid(String bid) throws AdminException {
		
		String message = "Bid Rejection Failed";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("UPDATE bidder SET status = ? where bid = ? AND status=? ");
			ps.setString(1,"Rejected");
			ps.setString(2,bid);
			ps.setString(3,"Pending");
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				
				message = "Bid has been Rejected succesfully";
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		return message ;
		
		
	}

	@Override
	public String bidTendor(String bid, String vid, String tid, int bamount,String deadline) throws AdminException {
		
		String message = "Tender Bidding Failed!";
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			String status = "Pending";
			
			PreparedStatement ps =conn.prepareStatement("insert into bidder values(?,?,?,?,?,?)");
			
			ps.setString(1, bid);
			ps.setString(2, vid);
			ps.setString(3, tid);
			ps.setInt(4, bamount);
			ps.setString(5, deadline);
			ps.setString(6, status);
			
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				message = "You have succesfully bid for the tendor";
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		return message;
		
		
	}

	@Override
	public List<Bidderbean> getAllBidsOnTendor(String tid) throws AdminException {
		
		List<Bidderbean> listOfBids = new ArrayList<>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("select * from bidder where tid=?");
			ps.setString(1, tid);
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				
				Bidderbean bbean = new Bidderbean(rs.getString("bid"), rs.getString("vid"),rs.getString("tid"), rs.getInt("bidamount"), rs.getString("deadline"), rs.getString("status"));
				
				listOfBids.add(bbean);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		return listOfBids;
	}

	@Override
	public List<Bidderbean> getAllBidsOfVendor(String vid) throws AdminException {
	
         List<Bidderbean> listOfBids = new ArrayList<Bidderbean>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("select * from bidder where vid=?");
			ps.setString(1, vid);
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				
				Bidderbean bbean = new Bidderbean(rs.getString("bid"), rs.getString("vid"),rs.getString("tid"), rs.getInt("bidamount"), rs.getString("deadline"), rs.getString("status"));
				
				listOfBids.add(bbean);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		return listOfBids;
		
	}
	
	
	

	
}
