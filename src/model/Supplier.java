package model;

public class Supplier {

	private String name;
	private String address;
	private String country;
	private String phoneNo;
	private String email;
	
	public Supplier(String name, String address, String Country, String phoneNo, String email) {
		this.name = name;
		this.address = address;
		this.country = Country;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public String getname() {
		return name;
	}
	
	public String getaddress() {
		return address;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getphoneNo() {
		return phoneNo;
	}
	
	public String getemail() {
		return email;
	}
	
}
