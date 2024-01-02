package lk.ijse.Model;

import lk.ijse.TM.bookTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String book_id;
    private String Member_Id;
    private String LocalDate;
    private int qty;
    private List<bookTm> tmList = new ArrayList<>();
}
