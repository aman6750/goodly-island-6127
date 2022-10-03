package com.TMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.TMS.bean.Bidderbean;
import com.TMS.bean.Vendorbean;
import com.TMS.bean.tenderbean;
import com.TMS.dao.BidderDao;
import com.TMS.dao.BidderDaoImpl;
import com.TMS.dao.TendorDao;
import com.TMS.dao.TendorDaoImpl;
import com.TMS.dao.VendorDaoImpl;
import com.TMS.dao.vendorDao;
import com.TMS.exception.AdminException;
import com.TMS.utility.DBUtil;

public class Main {
	
	public static void admin() {
		
		
		Scanner sc = new Scanner(System.in);
		
		  System.out.println("*********please enter your Admin Username************\n");
          
          String uname = sc.next();
          
          System.out.println(" \n*************** please enter your Admin Password***********");
          
          String upass = sc.next();
          
          try(Connection conn =DBUtil.provideConnection()) {
       	   
       	   PreparedStatement ps=conn.prepareStatement("select apass from admin where aname = ?");
       	   ps.setString(1, uname);
       	   
       	   ResultSet rs=ps.executeQuery();
       	   
       	   if(rs.next()) {
       		   
       		   String pass=rs.getString("apass");
       		   
       		   if(pass.equals(upass)) {
       			   
       			   System.out.println("\n Welcome as an Admin...!");
       			   
       			   while(true) {
       			   
       			   System.out.println(" \nSelect any one of the following: ");
       			   
       			   System.out.println("Select (2) to Register new Vendor."+"\n"+ "Select (3) to  View all the vendors."+"\n"+"Select (4) to  Create new tenders."+"\n"+ "Select (5) to View All the Tenders."+"\n"+"Select (6) to  View All the Bids of a tender."+"\n"+"Select (7) to  Assign tender to a vendor.");
       			
       			   System.out.println("Select (8) to  To remove tendor from list");
       			   System.out.println("Select (9) to  To update tendor ");

       			   
       			   System.out.println("Select (10) to  return back to admin menu");

       			   System.out.println("Select (11) to  return back to main menu");
       					   
       					   int k = sc.nextInt();
       			   
       			   switch (k) {
					case 2: {
						
						System.out.println("\n WELCOME TO REGISTER VENDOR");
						
						System.out.println("\n Enter Vendor id");
						String vid = sc.next();
						
						System.out.println("\n Enter Vendor name");
						String name = sc.next();
						
						System.out.println("\n Enter Vendor mobile Number");
						String mobile = sc.next();
						
						System.out.println("\n Enter Vendor email");
						String email = sc.next();
						
						System.out.println(" \n Enter Vendor password for register");
						String password = sc.next();
						
						System.out.println(" \n Enter Vendor Company name");
						String company = sc.next();
						
						System.out.println("\n Enter Vendor address");
						String address = sc.next();
						
						
						Vendorbean vb = new Vendorbean(vid,  password,  name,  mobile,  email,  company,
							 address);
						
						vendorDao dao = new VendorDaoImpl();
						
						String status =dao.registernewVendor(vb);
						
						System.out.println(status);
						
//						admin();
						
					break;
					}
					case 3:{
						
						System.out.println("\n LIST OF VENDORS:");
						
						vendorDao dao = new VendorDaoImpl();
						List<Vendorbean> list=dao.viewAllVendors();
						
						for(Vendorbean v: list) {
							System.out.println(v);
						}
						
//						admin();
						
						break;
					}
					case 4:{
						
						System.out.println("\n Enter tendor id");
						String tid = sc.next();
						
						System.out.println("\n Enter tendor name");
						String tname = sc.next();
						
						System.out.println("\n Enter tendor type");
						String ttype = sc.next();
						
						System.out.println("\n Enter tendor price");
						int tprice = sc.nextInt();
						
						System.out.println("\n enter description of the tendor");
						String tdesc = sc.next();
						
						System.out.println("\n Enter tendor deadline date");
						String tdeadline = sc.next();
						
						System.out.println("\n Enter tendor location");
						String tlocation = sc.next();
						
						
						tenderbean vb = new tenderbean( tid, tname, ttype, tprice, tdesc, tdeadline,tlocation);
						
						TendorDao dao = new TendorDaoImpl();
						
						String status =dao.CreatenewTendor(vb);
						
						System.out.println(status);
						
//						admin();
						break;
					}
					case 5:{
						System.out.println("\n LIST OF TENDORS:");
						
						
						TendorDao dao = new TendorDaoImpl();
						List<tenderbean> list =dao.getAllTenders();
						
						for(tenderbean t: list) {
							System.out.println(t);
						}
						
//						admin();
						break;
					}
					case 6:{
						
						System.out.println("\n Enter Tendor id :");
						
						String tid = sc.next();
						
						BidderDao dao = new BidderDaoImpl();
						
						List<Bidderbean> list=dao.getAllBidsOnTendor(tid);
						
						if(list.size()==0) {
							System.out.println("Currntly no bid is there on tendor.");
						}else {
							
							for(Bidderbean t: list) {
								System.out.println(t);
							}
						}
						
//						admin();
						break;
					}
					
					case 7:{
						
						System.out.println("\n  TO ASSIGN TENDOR TO VENDOR WE REQUIRE:");
						
						System.out.println(" ENTER VENDOR ID");
						String vid = sc.next();
						
						System.out.println("ENTER TENDOR ID");
						String tid = sc.next();
						
						System.out.println("ENTER BIDDER ID");
						String bid = sc.next();
						
						TendorDao dao = new TendorDaoImpl();
						String message =dao.assignTender(tid, vid, bid);
						
						System.out.println(message);
						
//						admin();
						break;
					}
					case 8:{
						
						System.out.println("\n Enter Tendor id : ");
						
						String tid = sc.next();
						
						TendorDao dao = new TendorDaoImpl();
						if(dao.removeTendor(tid)) {
							
							System.out.println("Tendor removed Sucessfully");
						}else {
							System.out.println("Tendor removed Failed");

						}
						
						
						
						break;
					}
					
					case 9:{
						
						System.out.println("Enter tendor id");
						String tid = sc.next();
						
						System.out.println("Enter tendor name");
						String tname = sc.next();
						
						System.out.println("Enter tendor type");
						String ttype = sc.next();
						
						System.out.println("Enter tendor price");
						int tprice = sc.nextInt();
						
						System.out.println("enter description of the tendor");
						String tdesc = sc.next();
						
						System.out.println("Enter tendor deadline date");
						String tdeadline = sc.next();
						
						System.out.println("Enter tendor location");
						String tlocation = sc.next();
						
						
						tenderbean vb = new tenderbean( tid, tname, ttype, tprice, tdesc, tdeadline,tlocation);
						
						TendorDao dao = new TendorDaoImpl();
						
						String message =dao.updateTender(vb);
						
						System.out.println(message);
						
						break;
					}
					
					case 10:{
						
						admin();
						break ;
						
					}
					case 11:{
						
						main(null);
						break ;
						
					}
						
					default:
						System.out.println("enter valid choice");
//						admin();
					}
       			   
       			   
       		   }
       		   }else {
       			   throw new AdminException("invalid password or username");
       		   }
       	   }else {
       		   throw new AdminException("no admin exist with this credentials..!");
       	   }
       	   
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

   	   
   	   
		
	}
	
	
	public static void vendor() {
		
		
		Scanner sc = new Scanner(System.in);
		  
        System.out.println("\n please enter your Username");
        
        String uname = sc.next();
        
        System.out.println("\n please enter your Password");
        
        String upass = sc.next();
        
        try(Connection conn =DBUtil.provideConnection()) {
     	   
     	   PreparedStatement ps=conn.prepareStatement("select * from vendor where vname = ? and password = ?");
     	   ps.setString(1, uname);
     	   ps.setString(2, upass);
     	   
     	   ResultSet rs=ps.executeQuery();
     	   
     	   if(rs.next()) {
     		   
     		   String pass=rs.getString("password");
     		   String name=rs.getString("vname");
     		   
     		   if((pass.equals(upass)) && (name.equals(uname))) {
     			   
     			   System.out.println("\n Welcome as an Vendor...!");
     			   
     			   
     			   while(true) {
     			   
     			   		System.out.println("Select any one of the following: ");
     			   
     			   System.out.println("Select (2) to  View all the current Tenders"+"\n"+ "Select (3) to Place a Bid against a Tender."+"\n"+"Select (4) to  View status of a Bid(Whether Selected or Not)."+"\n"+ "Select (5) to  View his own Bid History");
     			   
     			   System.out.println("Select (6) to  return to back vendor menu");

       			   System.out.println("Select (7) to  return to back main menu");

     					   
     					   int k = sc.nextInt();
     			   
     					   switch (k) {
							case 2: {
								
								System.out.println(" \n VIEWING ALL THE CURRENT TENDORS");
								
								
								
								
								TendorDao dao = new TendorDaoImpl();
								List<tenderbean> list =dao.getAllTenders();
								
								for(tenderbean t: list) {
									System.out.println(t);
								}
								
//								vendor();
								break;
								
							}
							
							case 3: {
								
								
								System.out.println("\n PLACING BID AGAINST TENDOR");
								
								System.out.println("ENTER BIDDER ID");
								String bid = sc.next();
								
								System.out.println("ENTER YOUR VENDOR ID:");
								String vid = sc.next();
								
								System.out.println("ENTER TENDOR ID YOU WANT TO BID ON :");
								String tid = sc.next();
								
								System.out.println("ENTER YOUR BIDDER AMOUNT:");
								int bamount = sc.nextInt();
								
								System.out.println("ENTER DEADLINE OF TENDOR ");
								String deadline = sc.next();
								
								BidderDao dao = new BidderDaoImpl();
								
								String message =dao.bidTendor(bid, vid, tid, bamount, deadline);
								
								System.out.println(message);
								
//								vendor();
								
								break;
								
							}
							
							case 4: {
								
								System.out.println("\n WELCOME TO View status of a Bid(Whether Selected or Not). ");
								
								
																	
								System.out.println("ENTER BIDDER ID");
								String bid = sc.next();
								
								System.out.println("ENTER YOUR TENDOR ID:");
								String tid = sc.next();
								
								System.out.println("ENTER YOUR VENDOR ID:");
								String vid = sc.next();
								
								System.out.println("\n ============ACCEPTANCE DETAILS===========");
								System.out.println();
								BidderDao dao = new BidderDaoImpl();
								String message = dao.acceptBid(bid, tid, vid);
								System.out.println(message);
								
								System.out.println("\n =============REJECTION DETAILS=============");
								
								System.out.println();
								
								String status =dao.rejectBid(bid);
								
								System.out.println(status);
								
//								vendor();
								
								break;
								
							}
							
							case 5: {

								System.out.println("\n View his own Bid History....");
								
								
								System.out.println("ENTER YOUR VENDOR ID:");
								String vid = sc.next();
								
								BidderDao dao = new BidderDaoImpl();

								List<Bidderbean> list =dao.getAllBidsOfVendor(vid);
								

								for(Bidderbean t: list) {
									System.out.println(t);
								}
								
//								vendor();
								break;
								
							}
							
							case 6:{
								
								vendor();
								break ;
								
							}
							case 7:{
								
								main(null);
								break ;
								
							}
							

							
							default:
								
								System.out.println("enter valid no.");
								vendor();
								
							}
     			   
     		   }
     		   }else {
     			   throw new AdminException("invalid password or username");
     		   }
     	   }else {
     		   throw new AdminException("no empanelled vendor exist with this credentials..!");
     	   }
     	   
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

 	   
		
	}


	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner (System.in);
		
		System.out.println("\n ((((((((((WELCOME TO TENDOR MANAGEMENT SYSTEM)))))))))))\n ");
		
           System.out.println("               There are 2 users in the system:");
           
           System.out.println("\n****************** select 1 for Admin******************");
           System.out.println("\n ******************select 2 for Vendor******************");
		

           int n = sc.nextInt();
           if(n==1) {
        	   
              admin();
              main(null);
               
             
           }else if(n==2){
        	           	   
        	 vendor();
        	 main(null);
        	   
           }else {
        	   
        	   System.out.println("!!!!!!!!!!!!!!!!!!rasta napoo!!!!!!!!!!!!!!!!!!");
        	   
           }

	}

}
