package model;

/**
 * Model class representing a clothing product.
 * 
 * This class extends Product and adds clothing-specific properties such as size and colour.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Clothing extends Product {

	private String size;
	private String colour;

	/**
	 * Constructor initializing a Clothing product with all details.
	 * 
	 * @param id the unique identifier
	 * @param productNo the product number
	 * @param name the product name
	 * @param minStock the minimum stock level
	 * @param reservedStock the reserved stock quantity
	 * @param price the product price
	 * @param size the clothing size
	 * @param colour the clothing colour
	 */
	public Clothing(int id, int productNo, String name, int minStock, int reservedStock, double price, String size,
			String colour) {

		super(id, productNo, name, minStock, reservedStock, price);
		this.size = size;
		this.colour = colour;
	}

	/**
	 * Gets the clothing size.
	 * 
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Gets the clothing colour.
	 * 
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}
}
