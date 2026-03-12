package controller;

import java.util.List;

import db.ProductDB;
import model.Product;

/**
 * Controller class for managing product-related operations.
 * 
 * This class provides methods to find and retrieve products from the database.
 * It acts as a bridge between the GUI/application layer and the database layer.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class ProductController {
    private ProductDB pDB;

    /**
     * Constructor initializing the ProductDB connection.
     */
    public ProductController() {
    	pDB = new ProductDB();

    }

    /**
     * Finds a product by product number.
     * 
     * @param productNo the product number to search for
     * @return the Product object if found, null otherwise
     */
    public Product findProduct(int productNo) {
        return pDB.findProduct(productNo);
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return a List containing all Product objects
     */
    public List<Product> getAllProducts() {
        return pDB.getAllProducts();
    }
}
