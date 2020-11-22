package com.posbackend.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "uniqueid")
    private int uniqueid;
    @Column(name = "saledate")
    private LocalDate saleDate;
    @Column(name = "customer")
    private int customer;
    @Type(type = "list-array")
    @Column(name = "items", columnDefinition = "text[]")
    private List<String> items;
    @Column(name = "paymentmethod")
    private String paymentMethod;
    @Column(name = "saleamount")
    private float saleAmount;
    @Column(name = "salesman")
    private int salesMan;

    public Sale() {
    }

    public Sale(LocalDate saleDate, int customer, List<String> items, String paymentMethod, float saleAmount, int salesMan) {
        this.saleDate = saleDate;
        this.customer = customer;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.saleAmount = saleAmount;
        this.salesMan = salesMan;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "uniqueid=" + uniqueid +
                ", saleDate=" + saleDate +
                ", customer=" + customer +
                ", items=" + items +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", saleAmount=" + saleAmount +
                ", salesMan=" + salesMan +
                '}';
    }

    public int getUniqueid() {
        return uniqueid;
    }
    public void setUniqueid(int uniqueid) {
        this.uniqueid = uniqueid;
    }
    public LocalDate getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
    public int getCustomer() {
        return customer;
    }
    public void setCustomer(int customer) {
        this.customer = customer;
    }
    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public float getSaleAmount() {
        return saleAmount;
    }
    public void setSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
    }
    public int getSalesMan() {
        return salesMan;
    }
    public void setSalesMan(int salesMan) {
        this.salesMan = salesMan;
    }
}
