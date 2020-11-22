package com.posbackend.service;

import com.posbackend.model.Sale;
import com.posbackend.repository.SaleRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class SaleServiceImpl_PGRTest {

    private SaleRepository mockDao = Mockito.mock(SaleRepository.class);
    private SaleServiceImpl_PGR svc = new SaleServiceImpl_PGR(mockDao);

    @Autowired
    SaleServiceImpl_PGR svcLive;

    //OffsetDateTime saleDate, int customer, String[] items, int saleAmount, int salesMan
    List<String> items = new ArrayList<>();
    Sale thisSale = new Sale();


    @Test
    void newSale() {
        assertThat(svcLive).isNotNull();
        String[] values = {"123456789012", "012345678912"};
        Collections.addAll(items, values);
        thisSale = new Sale(LocalDate.now(Clock.systemDefaultZone()), 26, items, "CreditCard", 3.99f, 1);
        assertThat(svcLive.newSale(thisSale)).isNotNull();
    }

    @Test
    void getSalesByCustemerId() {
        assertThat(svcLive).isNotNull();
        List<Sale> list = svcLive.getSalesByCustemerId(26);
        for(Sale e : list) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getSalesByDate() {
        assertThat(svcLive).isNotNull();
        List<Sale> list = svcLive.getSalesByDate(LocalDate.of(2020, 11, 21));
        for(Sale e : list) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getSalesByEmployeeId() {
        assertThat(svcLive).isNotNull();
        List<Sale> list = svcLive.getSalesByEmployeeId(1);
        for(Sale e : list) {
            System.out.println(e.toString());
        }
    }
}