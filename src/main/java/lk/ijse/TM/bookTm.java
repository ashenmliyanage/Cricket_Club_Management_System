package lk.ijse.TM;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class bookTm {
    private String ItemCode;
    private String item;
    private String count;
    private int qty;
    private Button btn;
}
