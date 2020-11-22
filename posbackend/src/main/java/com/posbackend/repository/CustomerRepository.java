package com.posbackend.repository;

import com.posbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional
    @Query("DELETE from Customer c where c.customerid = ?1")
    int deleteCustomer(int customerId);

    @Transactional
    int deleteByEmail(String email);
}
