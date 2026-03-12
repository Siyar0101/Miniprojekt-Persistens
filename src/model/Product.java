package model;

import java.time.LocalDate;

public class Product {

    private int id;             // primary key from DB
    private int productNo;      // business number (1001, 1002, 1003)
    private String name;
    private int minStock;
    private int reservedStock;
    private double price;


    public Product(int id, int productNo, String name, int minStock, int reservedStock, double price) {
        this.id = id;
        this.productNo = productNo;
        this.name = name;
        this.minStock = minStock;
        this.reservedStock = reservedStock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getProductNo() {
        return productNo;
    }

    public String getName() {
        return name;
    }

    public int getMinStock() {
        return minStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }
    
    public double getPrice() {
        return price;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public double getPrice(LocalDate date) {
        return 0; // implement later
    }

    public int calculateTotalAvailableStock() {
        return 0; // implement later
    }
}
