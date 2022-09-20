package DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Item {
    private final String name;
    private final BigDecimal price;
    private int inventory;

    public Item(String name, BigDecimal price, int inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, inventory);
    }

    @Override
    public String toString() {
        return name + ": " + "$" + price + ", Count: " + inventory;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return inventory == item.inventory && Objects.equals(name, item.name) && Objects.equals(price, item.price);
    }


}
