package pap;

import lombok.Data;

@Data
public class SupplierItem {
    int id;
    String name;
    int quantity;
    String comment;


    public SupplierItem(int id, String name, int quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public SupplierItem(int id, String name, int quantity, String comment){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.comment = comment;
    }
}
