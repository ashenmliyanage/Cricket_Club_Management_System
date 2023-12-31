package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private String Member_id;
    private String Full_name;
    private String Position;
    private String bod;
    private int Age;
    private String Email;
    private String Address;
    private InputStream image;
}
