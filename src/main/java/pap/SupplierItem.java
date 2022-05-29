package pap;

import lombok.Data;

@Data
public class SupplierItem extends Item{
    int quantity;

    public SupplierItem(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public SupplierItem(String name, int quantity, String description){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }
}
