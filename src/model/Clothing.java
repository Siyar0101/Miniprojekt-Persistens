package model;

public class Clothing extends Product {

	private String size;
	private String colour;

	public Clothing(int id, int productNo, String name, int minStock, int reservedStock, double price, String size,
			String colour) {

		super(id, productNo, name, minStock, reservedStock, price);
		this.size = size;
		this.colour = colour;
	}

	public String getSize() {
		return size;
	}

	public String getColour() {
		return colour;
	}
}
