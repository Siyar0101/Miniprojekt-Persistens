package model;

import java.time.LocalDate;

public class Product {

    private int id;              // ✔ matches Product.id in the database
    private String name;
    private int minStock;
    private int reservedStock;

    public Product(int id, String name, int minStock, int reservedStock) {
        this.id = id;
        this.name = name;
        this.minStock = minStock;
        this.reservedStock = reservedStock;
    }

    public int getId() { 
        return id; 
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

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice(LocalDate date) {
        return 0; // implement later
    }

    public int calculateTotalAvailableStock() {
        return 0; // implement later
    }
}
