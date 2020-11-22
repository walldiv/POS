package com.posbackend.service;

import com.posbackend.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    public Sale newSale(Sale sale);
    public List<Sale> getSalesByCustemerId(int customerId);
    public List<Sale> getSalesByDate(LocalDate date);
    public List<Sale> getSalesByEmployeeId(int employeeId);
}
