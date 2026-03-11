package controller;

import java.util.List;

import db.ProductDB;
import model.Product;

public class ProductController {
    private ProductDB pDB;

    public ProductController() {
        pDB = ProductDB.getInstance();
    }

    public Product findProduct(int productNo) {
        return pDB.findProduct(productNo);
    }

    public List<Product> getAllProducts() {
        return pDB.getAllProducts();
    }
}
