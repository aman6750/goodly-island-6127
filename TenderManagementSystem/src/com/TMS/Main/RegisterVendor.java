package com.TMS.Main;

import java.util.Scanner;

import com.TMS.bean.Vendorbean;
import com.TMS.dao.VendorDaoImpl;
import com.TMS.dao.vendorDao;

public class RegisterVendor {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
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
		
		
	}
}
