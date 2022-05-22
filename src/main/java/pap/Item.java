package pap;

import lombok.Data;
import lombok.Setter;

@Data
@Setter

public class Item {
    int id;
    String name;
    double price;
    String description;


    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    public Item(String name){
        this.name = name;
    }

    public Item() {

    }
}
