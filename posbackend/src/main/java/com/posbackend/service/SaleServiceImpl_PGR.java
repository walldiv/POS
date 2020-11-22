package com.posbackend.service;

import com.posbackend.model.Sale;
import com.posbackend.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl_PGR implements ISaleService {


    public SaleServiceImpl_PGR() {
    }

    public SaleServiceImpl_PGR(SaleRepository saleDao) {
        this.saleDao = saleDao;
    }

    @Autowired
    SaleRepository saleDao;

    @Override
    public Sale newSale(Sale sale) {
        return saleDao.save(sale);
    }

    @Override
    public List<Sale> getSalesByCustemerId(int customerId) {
        return saleDao.findAllByCustomer(customerId);
    }

    @Override
    public List<Sale> getSalesByDate(LocalDate date) {
        return saleDao.findAllBySaleDate(date);
    }

    @Override
    public List<Sale> getSalesByEmployeeId(int employeeId) {
        return saleDao.findAllBySalesMan(employeeId);
    }
}
