package com.posbackend.service;

import com.posbackend.model.Customer;
import com.posbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl_PGR implements ICustomerService {

    public CustomerServiceImpl_PGR() {
    }

    public CustomerServiceImpl_PGR(CustomerRepository custDao) {
        this.custDao = custDao;
    }

    @Autowired
    CustomerRepository custDao;

    @Override
    public boolean createNewCustomer(Customer customer) {
        return custDao.save(customer) != null;
    }

    @Override
    public int deleteCustomer(Customer customer) {
        return custDao.deleteByEmail(customer.getEmail());
        //return custDao.deleteCustomer(customer.getCustomerid()) > 0;
    }

    @Override
    public boolean editCustomer(Customer editedCustomer) {
       Optional<Customer> old = custDao.findById(editedCustomer.getCustomerid());
       Customer newCust = old.get();
       newCust = editedCustomer;
       return custDao.save(newCust) != null;
    }
}
