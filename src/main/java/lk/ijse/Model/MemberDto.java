package lk.ijse.Model;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String Member_id;
    private String Full_name;
    private String Position;
    private String bod;
    private int Age;
    private String Email;
    private String Address;
    private InputStream image;
}
