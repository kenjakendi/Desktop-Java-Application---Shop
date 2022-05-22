package pap;

import lombok.Data;

@Data
public class SupplierItem extends Item{
    int quantity;

    public SupplierItem(int id, String name, int quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public SupplierItem(int id, String name, int quantity, String description){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }
}
