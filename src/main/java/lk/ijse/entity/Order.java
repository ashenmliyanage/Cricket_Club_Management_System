package lk.ijse.entity;

import lk.ijse.TM.bookTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String book_id;
    private String Member_Id;
    private String LocalDate;
    private int qty;
    private List<bookTm> tmList = new ArrayList<>();
}
