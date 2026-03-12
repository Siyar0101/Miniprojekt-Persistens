package model;

public class Customer {

    private int id;              // ✔ added
    private String name; 
    private String address;
    private int zipcode;
    private String city;
    private String phoneNo; 
    private String email;
    private String customerType;

    public Customer(int id, String name, String address, int zipcode, String city, 
                    String phoneNo, String email, String customerType) {
        this.id = id;            // ✔ added
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
        this.customerType = customerType;
    }

    public int getId() {         // ✔ added
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerType() {
        return customerType;
    }
}
