package com.posbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventoryitem")
public class InventoryItem {
    @Id
    @Column(name = "upc", nullable = false)
    public String upc;
    @Column(name = "itemname", nullable = false)
    public String itemName;
    @Column(name = "category", nullable = false)
    public String category;
    @Column(name = "description", nullable = false)
    public String description;
    @Column(name = "itemcost", nullable = false)
    public float itemCost;
    @Column(name = "salevalue", nullable = false)
    public float saleValue;
    @Column(name = "itemvendor", nullable = false)
    public int itemVendor;
    @Column(name = "quantity")
    public int quantity;

    public InventoryItem() {
    }

    public InventoryItem(String upc, String itemName, String category, String description, float itemCost, float saleValue,
                         int itemVendor, int quantity) {
        this.upc = upc;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.itemCost = itemCost;
        this.saleValue = saleValue;
        this.itemVendor = itemVendor;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "upc='" + upc + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", itemCost=" + itemCost +
                ", saleValue=" + saleValue +
                ", itemVendor=" + itemVendor +
                ", quantity=" + quantity +
                '}';
    }

    public String getUpc() {
        return upc;
    }
    public void setUpc(String upc) {
        this.upc = upc;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getItemCost() {
        return itemCost;
    }
    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }
    public float getSaleValue() {
        return saleValue;
    }
    public void setSaleValue(float saleValue) {
        this.saleValue = saleValue;
    }
    public int getItemVendor() {
        return itemVendor;
    }
    public void setItemVendor(int itemVendor) {
        this.itemVendor = itemVendor;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
