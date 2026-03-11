package model;

public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(int productNo, String name, int minStock, int reservedStock, String size, String colour) {
        super(productNo, name, minStock, reservedStock);
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

