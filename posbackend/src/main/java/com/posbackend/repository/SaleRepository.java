package com.posbackend.repository;

import com.posbackend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAllByCustomer(int customerId);
    List<Sale> findAllBySaleDate(LocalDate date);
    List<Sale> findAllBySalesMan(int employeeId);
}
