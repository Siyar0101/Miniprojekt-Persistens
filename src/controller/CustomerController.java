package controller;

import db.CustomerDB;
import model.Customer;

/**
 * Controller class for managing customer-related operations.
 * 
 * This class provides methods to find customers by phone number or ID.
 * It acts as a bridge between the GUI/application layer and the database layer.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class CustomerController {

    private CustomerDB cDB;

    /**
     * Constructor initializing the CustomerDB connection.
     */
    public CustomerController() {
        cDB = new CustomerDB();   
    }

    /**
     * Finds a customer by phone number.
     * 
     * @param phoneNo the phone number of the customer to find
     * @return the Customer object if found, null otherwise
     */
    public Customer findCustomer(String phoneNo) {
        return cDB.findCustomer(phoneNo);
    }

    /**
     * Finds a customer by ID.
     * 
     * @param id the unique identifier of the customer
     * @return the Customer object if found, null otherwise
     */
    public Customer findCustomerById(int id) {
        return cDB.findCustomerById(id);   
}
    
}
