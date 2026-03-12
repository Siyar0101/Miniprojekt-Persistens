package model;

import java.time.LocalDate;

/**
 * Model class representing a price with a date.
 * 
 * This class stores a price amount along with the date when that price was effective.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Price {
	private double price;
	private LocalDate date;

	/**
	 * Constructor initializing a Price with amount and date.
	 * 
	 * @param price the price amount
	 * @param date the date when the price is effective
	 */
	public Price(double price, LocalDate date) {
		this.price = price;
		this.date = date;
	}

	/**
	 * Gets the price amount.
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the effective date of the price.
	 * 
	 * @return the LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}
}
