package model;

public class Stock {
    private Product product;
    private Warehouse warehouse;
    private int availableQty;

    public Stock(Product product, Warehouse warehouse, int availableQty) {
        this.product = product;
        this.warehouse = warehouse;
        this.availableQty = availableQty;
    }

    public Product getProduct() {
        return product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int qty) {
        this.availableQty = qty;
    }
}
