package model;

/**
 * Model class representing an equipment product.
 * 
 * This class extends Product and adds equipment-specific properties such as material and style.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Equipment extends Product {
    private String material;
    private String style;

    /**
     * Constructor initializing an Equipment product with all details.
     * 
     * @param id the unique identifier
     * @param productNo the product number
     * @param name the product name
     * @param minStock the minimum stock level
     * @param reservedStock the reserved stock quantity
     * @param material the equipment material
     * @param style the equipment style
     * @param price the product price
     */
    public Equipment(int id, int productNo, String name, int minStock, int reservedStock, String material, String style, double price) {
        super(id, productNo, name, minStock, reservedStock, price);
        this.material = material;
        this.style = style;
    }

	/**
	 * Gets the equipment material.
	 * 
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Gets the equipment style.
	 * 
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

}
