package model;

public class GunReplica extends Product {
    private String material;
    private String calibre;

    public GunReplica(int id, int productNo, String name, int minStock, int reservedStock, String material, String calibre) {
        super(id, productNo, name, minStock, reservedStock);
        this.material = material;
        this.calibre = calibre;
    }

	public String getMaterial() {
		return material;
	}

	public String getCalibre() {
		return calibre;
	}

}
