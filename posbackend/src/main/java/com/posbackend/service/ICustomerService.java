package com.posbackend.service;

import com.posbackend.model.Customer;

public interface ICustomerService {
    public boolean createNewCustomer(Customer customer);
    public int deleteCustomer(Customer customer);
    public boolean editCustomer(Customer editedCustomer);
}
