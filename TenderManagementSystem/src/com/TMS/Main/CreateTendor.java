package com.TMS.Main;

import java.util.Scanner;

import com.TMS.bean.Vendorbean;
import com.TMS.dao.VendorDaoImpl;
import com.TMS.dao.vendorDao;

public class CreateTendor {

public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter tendor id");
		String tid = sc.next();
		
		System.out.println("Enter tendor name");
		String tname = sc.next();
		
		System.out.println("Enter tendor type");
		String ttype = sc.next();
		
		System.out.println("Enter tendor price");
		String tprice = sc.next();
		
		System.out.println("enter description of the tendor");
		String tdesc = sc.next();
		
		System.out.println("Enter tendor deadline date");
		String tdeadline = sc.next();
		
		System.out.println("Enter tendor location");
		String tlocation = sc.next();
		
		
		Vendorbean vb = new Vendorbean( tid, tname, ttype, tprice, tdesc, tdeadline,tlocation);
		
		vendorDao dao = new VendorDaoImpl();
		
		String status =dao.registernewVendor(vb);
		
		System.out.println(status);
}
}
