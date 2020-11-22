package com.posbackend.service;

import com.posbackend.model.InventoryItem;

public interface IInventoryService {
    public boolean addNewItem(InventoryItem item);
    public boolean removeItem(InventoryItem item);
    public boolean editItem(InventoryItem item);
    public int addItemCount(InventoryItem item, int qty);

    //future method for ordering via email
    public void orderItem(InventoryItem item, int qty);
}
