package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Stock {
    private String code;
    private String Domain;
    private String Domain_Name;
    private String Type;
    private String Date;
    private String Member;
    private int Count;
}
