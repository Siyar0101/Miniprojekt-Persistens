package model;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    // Attributter fra diagrammet
    private String number;
    private String name;
    private String description;
    private List<Stock> stockItems; // Repræsentationen af 1..* relationen

    // Konstruktør: +create(number: String, name: String, description: String)
    public Warehouse(String number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.stockItems = new ArrayList<>(); // Initialiserer listen
    }

    // Metoder (Getters)
    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Metoder til håndtering af Stock-objekter
    public List<Stock> getStockItems() {
        return stockItems;
    }

    public void addStockItem(Stock stock) {
        if (stock != null) {
            this.stockItems.add(stock);
        }
    }
}