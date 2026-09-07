package model.TM;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter@Getter
@ToString
public class ItemTM {
    private String id;
    private String Description;
    private String packsize;
    private double unitprice;
    private int quantity;

}
