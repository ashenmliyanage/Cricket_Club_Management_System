package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private String code;
    private String Domain;
    private String Domain_Name;
    private String Type;
    private String Date;
    private String Member;
    private int Count;
}
