package com.posbackend.service;

import com.posbackend.model.Employee;
import com.posbackend.model.InventoryItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImpl_PGRTest {
    @Autowired
    private InventoryServiceImpl_PGR invSvc;

    //String upc, String itemName, String category, String description, float itemCost, float saleValue, int itemVendor, int quantity
    private InventoryItem tmpItem = new InventoryItem("123456789012", "Cheerios", "Cereal", "16oz box",
            2.99f, 3.99f, 37, 1);

    @Test
    public void testInvAddItem() {
        assertThat(invSvc).isNotNull();
        boolean test = invSvc.addNewItem(tmpItem);
        Assert.assertTrue("ITEM NOT ADDED", test);
    }

    @Test
    public void testInvDeleteItem() {
        assertThat(invSvc).isNotNull();
        boolean test = invSvc.removeItem(tmpItem);
        Assert.assertTrue("ITEM NOT REMOVED", test);
    }

    @Test
    public void testInvDeductQty() {
        assertThat(invSvc).isNotNull();
        int result = invSvc.addItemCount(tmpItem, 4);
        Assert.assertTrue("ITEM WAS LESS THAN 0", result >= 0);
    }

}