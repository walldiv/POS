package com.posbackend.service;

import com.posbackend.model.InventoryItem;
import com.posbackend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl_PGR implements IInventoryService{

    public InventoryServiceImpl_PGR() {
    }

    public InventoryServiceImpl_PGR(InventoryRepository invDao) {
        this.invDao = invDao;
    }

    @Autowired
    InventoryRepository invDao;

    @Override
    public boolean addNewItem(InventoryItem item) {
        return invDao.save(item) != null;
    }

    @Override
    public boolean removeItem(InventoryItem item) {
        try {
            invDao.deleteById(item.getUpc());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editItem(InventoryItem item) {
        return invDao.save(item) != null;
    }

    @Override
    public int addItemCount(InventoryItem item, int qty) {
        InventoryItem tmp = invDao.findByupc(item.getUpc());
        if(tmp != null) {
            int q = tmp.getQuantity() + qty;
            if(q >= 0){
                q = Integer.max(q, 0);
                tmp.setQuantity(q);
                invDao.save(tmp);
                System.out.printf("FINAL ITEM: %s", tmp.toString());
                return tmp.getQuantity();
            }
            else return -1;
        }
        else return -1;
    }


    //future method for ordering via email
    @Override
    public void orderItem(InventoryItem item, int qty) {

    }
}
