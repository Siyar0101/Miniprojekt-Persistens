package controller;

import db.CustomerDB;
import model.Customer;

public class CustomerController {

    private CustomerDB cDB;

    public CustomerController() {
        cDB = new CustomerDB();   // ✔ create a normal instance
    }

    public Customer findCustomer(String phoneNo) {
        return cDB.findCustomer(phoneNo);
    }

    public Customer findCustomerById(int id) {
        return cDB.findCustomerById(id);   // ✔ no more getInstance()
    }
}
