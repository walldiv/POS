package com.posbackend.service;

import com.posbackend.model.Customer;
import com.posbackend.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceImpl_PGRTest {

    private Customer tmp = new Customer("Bob", "Wilson", "6167426723", "1234 Memory Lane",
            "Kentwood", "MI", "Bob@mail.com");

    private CustomerRepository mockDao = Mockito.mock(CustomerRepository.class);
    private CustomerServiceImpl_PGR svc = new CustomerServiceImpl_PGR(mockDao);

    @Autowired
    CustomerServiceImpl_PGR svcLive;

    @Test
    void createNewCustomer_LIVE() {
        assertThat(svcLive).isNotNull();
        boolean result = svcLive.createNewCustomer(tmp);
        Assert.assertTrue("NO CUSTOMER MADE", result);
    }

    @Test
    void createNewCustomer_MOCKED() {
        given(mockDao.save(eq(tmp))).willReturn(tmp);
        boolean result = svc.createNewCustomer(tmp);
        then(mockDao).should().save(eq(tmp));
        assertThat(result != false);
    }

    @Test
    void deleteCustomer() {
        assertThat(svcLive).isNotNull();
        int result = svcLive.deleteCustomer(tmp);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void deleteCustomer_MOCKED() {
        given(mockDao.deleteByEmail(eq(tmp.getEmail()))).willReturn(1);
        int result = svc.deleteCustomer(tmp);
        then(mockDao).should().deleteByEmail(eq(tmp.getEmail()));
        assertThat(result > 0);
    }


    @Test
    void editCustomer() {
        assertThat(svcLive).isNotNull();
        tmp.setCustomerid(26);
        tmp.setFirstName("Andy");
        tmp.setLastName("Miles");
        boolean result = svcLive.editCustomer(tmp);
        Assert.assertTrue("NO CUSTOMER EDIT PERFORMED", result);
    }


}