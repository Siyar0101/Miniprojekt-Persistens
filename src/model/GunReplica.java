package model;

/**
 * Model class representing a gun replica product.
 * 
 * This class extends Product and adds gun replica-specific properties such as material and calibre.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class GunReplica extends Product {
    private String material;
    private String calibre;

    /**
     * Constructor initializing a GunReplica product with all details.
     * 
     * @param id the unique identifier
     * @param productNo the product number
     * @param name the product name
     * @param minStock the minimum stock level
     * @param reservedStock the reserved stock quantity
     * @param material the replica material
     * @param calibre the replica calibre
     * @param price the product price
     */
    public GunReplica(int id, int productNo, String name, int minStock, int reservedStock, String material, String calibre, double price) {
        super(id, productNo, name, minStock, reservedStock, price);
        this.material = material;
        this.calibre = calibre;
    }

	/**
	 * Gets the replica material.
	 * 
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Gets the replica calibre.
	 * 
	 * @return the calibre
	 */
	public String getCalibre() {
		return calibre;
	}

}
