package model.entity;



import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter@Getter
@ToString
public class Item{
    private String id;
    private String Description;
    private String packsize;
    private double unitprice;
    private int quantity;

}

