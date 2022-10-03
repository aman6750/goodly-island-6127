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
		
		  System.out.println("please enter your Username");
          
          String uname = sc.next();
          
          System.out.println("please enter your Password");
          
          String upass = sc.next();
          
          try(Connection conn =DBUtil.provideConnection()) {
       	   
       	   PreparedStatement ps=conn.prepareStatement("select apass from admin where aname = ?");
       	   ps.setString(1, uname);
       	   
       	   ResultSet rs=ps.executeQuery();
       	   
       	   if(rs.next()) {
       		   
       		   String pass=rs.getString("apass");
       		   
       		   if(pass.equals(upass)) {
       			   
       			   System.out.println("Welcome as an Admin...!");
       			   
       			   while(true) {
       			   
       			   System.out.println("Select any one of the following: ");
       			   
       			   System.out.println("2. Register new Vendor."+"\n"+ "3. View all the vendors."+"\n"+"4. Create new tenders."+"\n"+ "5. View All the Tenders."+"\n"+"6. View All the Bids of a tender."+"\n"+"7. Assign tender to a vendor.");
       			
       			   System.out.println("8. return back to admin menu");

       			   System.out.println("9. return back to main menu");
       					   
       					   int k = sc.nextInt();
       			   
       			   switch (k) {
					case 2: {
						
						System.out.println("WELCOME TO REGISTER VENDOR");
						
						System.out.println("Enter Vendor id");
						String vid = sc.next();
						
						System.out.println("Enter Vendor name");
						String name = sc.next();
						
						System.out.println("Enter Vendor mobile Number");
						String mobile = sc.next();
						
						System.out.println("Enter Vendor email");
						String email = sc.next();
						
						System.out.println("Enter Vendor password for register");
						String password = sc.next();
						
						System.out.println("Enter Vendor Company name");
						String company = sc.next();
						
						System.out.println("Enter Vendor address");
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
						
						System.out.println("LIST OF VENDORS:");
						
						vendorDao dao = new VendorDaoImpl();
						List<Vendorbean> list=dao.viewAllVendors();
						
						for(Vendorbean v: list) {
							System.out.println(v);
						}
						
//						admin();
						
						break;
					}
					case 4:{
						
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
						
						String status =dao.CreatenewTendor(vb);
						
						System.out.println(status);
						
//						admin();
						break;
					}
					case 5:{
						System.out.println("LIST OF TENDORS:");
						
						
						TendorDao dao = new TendorDaoImpl();
						List<tenderbean> list =dao.getAllTenders();
						
						for(tenderbean t: list) {
							System.out.println(t);
						}
						
//						admin();
						break;
					}
					case 6:{
						
						System.out.println("Enter Tendor id :");
						
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
						
						System.out.println(" TO ASSIGN TENDOR TO VENDOR WE REQUIRE:");
						
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
						
						admin();
						break ;
						
					}
					case 9:{
						
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
		  
        System.out.println("please enter your Username");
        
        String uname = sc.next();
        
        System.out.println("please enter your Password");
        
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
     			   
     			   System.out.println("Welcome as an Vendor...!");
     			   
     			   
     			   while(true) {
     			   
     			   		System.out.println("Select any one of the following: ");
     			   
     			   System.out.println("2. View all the current Tenders"+"\n"+ "3.Place a Bid against a Tender."+"\n"+"4. View status of a Bid(Whether Selected or Not)."+"\n"+ "5. View his own Bid History");
     			   
     			   System.out.println("6. return to back vendor menu");

       			   System.out.println("7. return to back main menu");

     					   
     					   int k = sc.nextInt();
     			   
     					   switch (k) {
							case 2: {
								
								System.out.println("VIEWING ALL THE CURRENT TENDORS");
								
								
								
								
								TendorDao dao = new TendorDaoImpl();
								List<tenderbean> list =dao.getAllTenders();
								
								for(tenderbean t: list) {
									System.out.println(t);
								}
								
//								vendor();
								break;
								
							}
							
							case 3: {
								
								
								System.out.println("PLACING BID AGAINST TENDOR");
								
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
								
								System.out.println("WELCOME TO View status of a Bid(Whether Selected or Not). ");
								
								
																	
								System.out.println("ENTER BIDDER ID");
								String bid = sc.next();
								
								System.out.println("ENTER YOUR TENDOR ID:");
								String tid = sc.next();
								
								System.out.println("ENTER YOUR VENDOR ID:");
								String vid = sc.next();
								
								System.out.println("============ACCEPTANCE DETAILS===========");
								System.out.println();
								BidderDao dao = new BidderDaoImpl();
								String message = dao.acceptBid(bid, tid, vid);
								System.out.println(message);
								
								System.out.println("=============REJECTION DETAILS=============");
								
								System.out.println();
								
								String status =dao.rejectBid(bid);
								
								System.out.println(status);
								
//								vendor();
								
								break;
								
							}
							
							case 5: {

								System.out.println("View his own Bid History....");
								
								
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
		
           System.out.println("There are 2 users in the system:");
           
           System.out.println("select 1 for Administrator");
           System.out.println("select 2 for Vendor");
		

           int n = sc.nextInt();
           if(n==1) {
        	   
              admin();
              main(null);
               
             
           }else if(n==2){
        	           	   
        	 vendor();
        	 main(null);
        	   
           }else {
        	   
        	   System.out.println("rasta napoo");
        	   
           }

	}

}
