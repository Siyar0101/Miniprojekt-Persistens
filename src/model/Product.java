package model;

public class Product {
    private int productNo;
    private String name;
    private int minStock;
    private int reservedStock;

    public Product(int productNo, String name, int minStock, int reservedStock) {
        this.productNo = productNo;
        this.name = name;
        this.minStock = minStock;
        this.reservedStock = reservedStock;
    }

    public int getProductNo() { return productNo; }
    public String getName() { return name; }
    public int getMinStock() { return minStock; }
    public int getReservedStock() { return reservedStock; }

    public double getPrice(LocalDate date) {
        // Implement price lookup logic
        return 0;
    }

    public int calculateTotalAvailableStock() {
        // Implement stock summation logic
        return 0;
    }
}

