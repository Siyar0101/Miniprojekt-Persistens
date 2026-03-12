package controller;

import java.util.List;

import db.CustomerDB;
import model.Customer;

public class CustomerController {
    private CustomerDB cDB;

    public CustomerController() {
        cDB = new CustomerDB();
    }

    public Customer findCustomer(String phoneNo) {
        return cDB.findCustomer(phoneNo);
    }

    public List<Customer> getAllCustomers() {
        return cDB.getAllCustomers();
    }
}
