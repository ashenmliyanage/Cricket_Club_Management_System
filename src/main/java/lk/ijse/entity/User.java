package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String User_id;
    private String name;
    private String Address;
    private int Age;
    private String Mail;
    private String Username;
    private String Password;
    private InputStream image;
}
