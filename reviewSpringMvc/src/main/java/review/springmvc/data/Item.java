package review.springmvc.data;

import lombok.Data;

@Data
public class Item {
    private String itemName;
    private int id;
    private int price;
    private int quantity;

    public Item(String itemName, int price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
