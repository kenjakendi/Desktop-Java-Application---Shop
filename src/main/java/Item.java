import lombok.Data;

@Data
public class Item {
    int id;
    String name;
    int price;
    String description;

    public Item(String name){
        this.name = name;
    }
}
