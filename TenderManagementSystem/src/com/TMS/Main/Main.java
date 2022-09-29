package com.TMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.TMS.exception.AdminException;
import com.TMS.utility.DBUtil;

public class Main {
	
	public static boolean isadminlogin = false; 
	
	public static boolean isvendorlogin = false; 

	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner (System.in);
		
           System.out.println("There are 2 users in the system:");
           
           System.out.println("select 1 for Administrator");
           System.out.println("select 2 for Vendor");
		

           int n = sc.nextInt();
           if(n==1) {
        	   
              
               
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
            			   
            			   isadminlogin=true;
            			   
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

        	   
        	   
           }else if(n==2){
        	   
        	   
        	   
               System.out.println("please enter your Username");
               
               String uname = sc.next();
               
               System.out.println("please enter your Password");
               
               String upass = sc.next();
               
               try(Connection conn =DBUtil.provideConnection()) {
            	   
            	   PreparedStatement ps=conn.prepareStatement("select * from vendor where vname = ? and password = ?");
            	   ps.setString(1, uname);
            	   ps.setString(1, upass);
            	   
            	   ResultSet rs=ps.executeQuery();
            	   
            	   if(rs.next()) {
            		   
            		   String pass=rs.getString("password");
            		   String name=rs.getString("vname");
            		   
            		   if((pass.equals(upass)) && (name.equals(uname))) {
            			   
            			   System.out.println("Welcome as an Vendor...!");
            			   
            			   isvendorlogin=true;
            			   
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

        	   
        	   
           }else {
        	   System.out.println("rasta napoo");
           }

	}

}
