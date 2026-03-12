package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private int id; // ✔ real primary key
	private Customer customer;
	private List<OrderLine> orderLines = new ArrayList<>();
	private LocalDateTime date;
	private double amount;
	private double discountGiven;

	public Order() {
		this.date = LocalDateTime.now();
	}

	public void addCustomer(Customer c) {
		this.customer = c;
	}

	public void addOrderLine(OrderLine ol) {
		orderLines.add(ol);
	}

	public double calculateTotal() {
		double total = 0;
		for (OrderLine ol : orderLines) {
			total += ol.calculateSubtotal();
		}
		amount = total - discountGiven;
		return amount;
	}

	// getters
	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	public double getDiscountGiven() {
		return discountGiven;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer c) {
		this.customer = c;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDiscountGiven(double discountGiven) {
		this.discountGiven = discountGiven;
	}
}
