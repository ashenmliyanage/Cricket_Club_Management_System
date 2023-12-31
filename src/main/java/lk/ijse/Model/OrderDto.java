package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String Book_id;
    private String Member_id;
    private String Date;
    private int Count;
}
