package model;

/**
 * Model class representing a customer.
 * 
 * This class encapsulates customer information such as name, address, contact details,
 * and customer type.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Customer {

    private int id;
    private String name;
    private String address;
    private int zipcode;
    private String city;
    private String phoneNo; 
    private String email;
    private String customerType;

    /**
     * Constructor initializing a Customer with all details.
     * 
     * @param id the unique identifier
     * @param name the customer's name
     * @param address the customer's address
     * @param zipcode the customer's zipcode
     * @param city the customer's city
     * @param phoneNo the customer's phone number
     * @param email the customer's email
     * @param customerType the customer's type (e.g., "club", "regular")
     */
    public Customer(int id, String name, String address, int zipcode, String city, 
                    String phoneNo, String email, String customerType) {
    	
        this.id = id;            
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
        this.customerType = customerType;
    }

    /**
     * Gets the customer ID.
     * 
     * @return the unique identifier
     */
    public int getId() {        
        return id;
    }

    /**
     * Gets the customer's name.
     * 
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's address.
     * 
     * @return the customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the customer's zipcode.
     * 
     * @return the customer's zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * Gets the customer's city.
     * 
     * @return the customer's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the customer's phone number.
     * 
     * @return the customer's phone number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Gets the customer's email.
     * 
     * @return the customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the customer's type.
     * 
     * @return the customer's type
     */
    public String getCustomerType() {
        return customerType;
    }
}
