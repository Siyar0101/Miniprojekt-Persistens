package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import db.ProductDB;
import model.Product;

/**
 * Test class for verifying the functionality of the ProductDB class.
 * 
 * This class contains unit tests that ensure correct behavior when retrieving
 * products from the database using product numbers. The tests validate that
 * valid lookups return Product objects, while invalid lookups return null.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class ProductDBTest {

    /**
     * Tests that findProduct() returns a Product object when a valid
     * product number exists in the database.
     * 
     * Purpose: Validate successful product lookup.
     * Return data: Product object (not null)
     */
    @Test
    void testFindProduct_valid() {
        ProductDB db = new ProductDB();
        Product p = db.findProduct(1001);

        assertNotNull(p, "Product should be found for valid product number");
        assertEquals(1001, p.getProductNo(), "Product number should match");
    }

    /**
     * Tests that findProduct() returns null when the product number does not
     * exist in the database.
     * 
     * Purpose: Ensure invalid product numbers return null.
     * Return data: null
     */
    @Test
    void testFindProduct_invalid() {
        ProductDB db = new ProductDB();
        Product p = db.findProduct(9999);

        assertNull(p, "Invalid product number should return null");
    }
}
