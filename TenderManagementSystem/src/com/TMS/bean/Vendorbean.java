package com.TMS.bean;

public class Vendorbean {

	
	private String vid;
	private String password;
	private String vname;
	private String vmobile;
	
	private String vemail;
	private String vcompany;
	
	private String vaddres;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVcompany() {
		return vcompany;
	}

	public void setVcompany(String vcompany) {
		this.vcompany = vcompany;
	}

	public String getVaddres() {
		return vaddres;
	}

	public void setVaddres(String vaddres) {
		this.vaddres = vaddres;
	}

	public Vendorbean(String vid, String password, String vname, String vmobile, String vemail, String vcompany,
			String vaddres) {
		super();
		this.vid = vid;
		this.password = password;
		this.vname = vname;
		this.vmobile = vmobile;
		this.vemail = vemail;
		this.vcompany = vcompany;
		this.vaddres = vaddres;
	}

	@Override
	public String toString() {
		return "Vendorbean [vid=" + vid + ", password=" + password + ", vname=" + vname + ", vmobile=" + vmobile
				+ ", vemail=" + vemail + ", vcompany=" + vcompany + ", vaddres=" + vaddres + "]";
	}
	
	public Vendorbean() {
		// TODO Auto-generated constructor stub
	}
}
