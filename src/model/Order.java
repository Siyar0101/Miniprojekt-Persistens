package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a sales order.
 * 
 * This class manages order information including customer details, order lines,
 * and order totals with discount calculations.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Order {

	private int id; 
	private Customer customer;
	private List<OrderLine> orderLines = new ArrayList<>();
	private LocalDateTime date;
	private double amount;
	private double discountGiven;

	/**
	 * Constructor initializing a new Order with current date.
	 */
	public Order() {
		this.date = LocalDateTime.now();
	}

	/**
	 * Adds a customer to the order.
	 * 
	 * @param c the Customer to add
	 */
	public void addCustomer(Customer c) {
		this.customer = c;
	}

	/**
	 * Adds an order line to the order.
	 * 
	 * @param ol the OrderLine to add
	 */
	public void addOrderLine(OrderLine ol) {
		orderLines.add(ol);
	}

	/**
	 * Calculates the total amount with discount if applicable.
	 * 
	 * Applies a 10% discount for "club" type customers when total is >= 1500.
	 * 
	 * @return the final amount after discount
	 */
	public double calculateTotal() {
		double total = 0.0;
		for (OrderLine ol : orderLines) {
			total += ol.calculateSubtotal();
		}

		// Default no discount
		discountGiven = 0.0;

		// Apply a 10% discount for customers of type "club" when total >= 1500
		if (customer != null && customer.getCustomerType() != null) {
			String type = customer.getCustomerType().trim();
			if (!type.isEmpty() && type.equalsIgnoreCase("club") && total >= 1500.0) {
				double rawDiscount = total * 0.10;
				// round to 2 decimals (cents)
				discountGiven = Math.round(rawDiscount * 100.0) / 100.0;
			}
		}

		amount = total - discountGiven;
		// Round amount to 2 decimals as well
		amount = Math.round(amount * 100.0) / 100.0;
		return amount;
	}

	/**
	 * Gets the order ID.
	 * 
	 * @return the order ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the customer associated with the order.
	 * 
	 * @return the Customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Gets all order lines in this order.
	 * 
	 * @return a List of OrderLine objects
	 */
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	/**
	 * Gets the order date.
	 * 
	 * @return the LocalDateTime of the order
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Gets the total amount including discounts.
	 * 
	 * @return the amount after discount
	 */
	public double getAmount() {
		// Ensure returned amount includes any discounts
		return calculateTotal();
	}

	/**
	 * Gets the discount amount given.
	 * 
	 * @return the discount amount
	 */
	public double getDiscountGiven() {
		return discountGiven;
	}

	/**
	 * Sets the order ID.
	 * 
	 * @param id the order ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the customer for the order.
	 * 
	 * @param c the Customer to set
	 */
	public void setCustomer(Customer c) {
		this.customer = c;
	}

	/**
	 * Sets the order lines.
	 * 
	 * @param orderLines the List of OrderLine objects to set
	 */
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	/**
	 * Sets the order date.
	 * 
	 * @param date the LocalDateTime to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Sets the discount amount.
	 * 
	 * @param discountGiven the discount amount to set
	 */
	public void setDiscountGiven(double discountGiven) {
		this.discountGiven = discountGiven;
	}
}