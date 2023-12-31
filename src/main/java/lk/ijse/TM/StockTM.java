package lk.ijse.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockTM {
    private String itemCode;
    private String type;
    private int count;

    @Override
    public String toString() {
        return "StockTM{" +
                "itemCode='" + itemCode + '\'' +
                ", type='" + type + '\'' +
                ", count=" + count +
                '}';
    }
}

