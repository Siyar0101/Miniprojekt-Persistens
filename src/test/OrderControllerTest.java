package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.OrderController;
import model.Customer;
import model.Order;
import model.OrderLine;

/**
 * Test class for verifying the functionality of the OrderController.
 * 
 * This class contains unit tests that ensure correct behavior when creating,
 * modifying, and confirming orders. Reflection is used to access the private
 * order field, since the controller does not expose a getter.
 * 
 * The tests validate order creation, customer assignment, product addition,
 * order confirmation, and cancellation.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderControllerTest {

    private OrderController oc;

    /**
     * Sets up a fresh OrderController before each test.
     */
    @BeforeEach
    void setup() {
        oc = new OrderController();
    }

    /**
     * Helper method to access the private 'order' field inside OrderController.
     * 
     * @return the current Order object stored inside the controller
     */
    private Order getPrivateOrder() {
        try {
            Field f = OrderController.class.getDeclaredField("order");
            f.setAccessible(true);
            return (Order) f.get(oc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests that placeOrder() correctly creates a new Order object.
     * Ensures a new order is initialized properly.
     */
    @Test
    void testPlaceOrder_createsOrderObject() {
        oc.placeOrder();
        Order o = getPrivateOrder();

        assertNotNull(o, "placeOrder() should create a new Order object");
        assertEquals(0, o.getOrderLines().size(), "New order should have no order lines");
        assertEquals(0, o.getAmount(), "New order should start with amount = 0");
    }

    /**
     * Tests that addCustomer() successfully adds a valid customer to the order.
     * Validates customer lookup and assignment.
     */
    @Test
    void testAddCustomer_validPhone() {
        oc.placeOrder();
        Customer c = oc.addCustomer("12345678");

        assertNotNull(c, "Customer should be found");
        Order o = getPrivateOrder();
        assertEquals(c, o.getCustomer(), "Customer should be added to the order");
    }

    /**
     * Tests that addCustomer() returns null when the phone number does not exist.
     * Ensures invalid phone numbers do not modify the order.
     */
    @Test
    void testAddCustomer_invalidPhone() {
        oc.placeOrder();
        Customer c = oc.addCustomer("99999999");

        assertNull(c, "Invalid phone number should return null");
        Order o = getPrivateOrder();
        assertNull(o.getCustomer(), "Order should not have a customer");
    }

    /**
     * Tests that addProduct() successfully adds a valid product to the order.
     * Validates product lookup and order line creation.
     */
    @Test
    void testAddProduct_validProduct() {
        oc.placeOrder();
        oc.addCustomer("12345678");

        OrderLine ol = oc.addProduct(1001, 1);

        assertNotNull(ol, "OrderLine should be created for valid product");
        Order o = getPrivateOrder();
        assertEquals(1, o.getOrderLines().size(), "Order should contain one order line");
    }

    /**
     * Tests that addProduct() returns null when the product number does not exist.
     * Ensures invalid product numbers do not modify the order.
     */
    @Test
    void testAddProduct_invalidProduct() {
        oc.placeOrder();
        oc.addCustomer("12345678");

        OrderLine ol = oc.addProduct(9999, 1);

        assertNull(ol, "Invalid product should return null");
        Order o = getPrivateOrder();
        assertEquals(0, o.getOrderLines().size(), "Order should not contain order lines");
    }

    /**
     * Tests that confirmOrder() saves the order and returns it.
     * Validates order saving and ID assignment.
     */
    @Test
    void testConfirmOrder_savesOrder() {
        oc.placeOrder();
        oc.addCustomer("12345678");
        oc.addProduct(1001, 1);

        Order confirmed = oc.confirmOrder();

        assertNotNull(confirmed, "confirmOrder() should return the order");
        assertTrue(confirmed.getId() > 0, "Order should be saved and have an ID");
    }

    /**
     * Tests that cancelOrder() removes the active order.
     * Ensures cancellation clears the order reference.
     */
    @Test
    void testCancelOrder_setsOrderToNull() {
        oc.placeOrder();
        oc.cancelOrder();

        Order o = getPrivateOrder();
        assertNull(o, "cancelOrder() should remove the active order");
    }
}
