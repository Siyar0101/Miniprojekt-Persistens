package model;

/**
 * Model class representing a supplier.
 * 
 * This class stores supplier information including name, address, contact details.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Supplier {

	private String name;
	private String address;
	private String country;
	private String phoneNo;
	private String email;
	
	/**
	 * Constructor initializing a Supplier with all details.
	 * 
	 * @param name the supplier's name
	 * @param address the supplier's address
	 * @param country the supplier's country
	 * @param phoneNo the supplier's phone number
	 * @param email the supplier's email
	 */
	public Supplier(String name, String address, String country, String phoneNo, String email) {
		this.name = name;
		this.address = address;
		this.country = country;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	/**
	 * Gets the supplier's name.
	 * 
	 * @return the supplier's name
	 */
	public String getname() {
		return name;
	}
	
	/**
	 * Gets the supplier's address.
	 * 
	 * @return the supplier's address
	 */
	public String getaddress() {
		return address;
	}
	
	/**
	 * Gets the supplier's country.
	 * 
	 * @return the supplier's country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Gets the supplier's phone number.
	 * 
	 * @return the supplier's phone number
	 */
	public String getphoneNo() {
		return phoneNo;
	}
	
	/**
	 * Gets the supplier's email.
	 * 
	 * @return the supplier's email
	 */
	public String getemail() {
		return email;
	}
	
}
