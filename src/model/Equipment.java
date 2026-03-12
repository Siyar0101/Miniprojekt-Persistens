package model;

public class Equipment extends Product {
    private String material;
    private String style;

    public Equipment(int id, int productNo, String name, int minStock, int reservedStock, String material, String style) {
        super(id, productNo, name, minStock, reservedStock);
        this.material = material;
        this.style = style;
    }

	public String getMaterial() {
		return material;
	}

	public String getStyle() {
		return style;
	}

}
