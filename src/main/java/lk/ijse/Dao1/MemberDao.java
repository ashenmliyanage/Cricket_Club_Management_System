package lk.ijse.Dao1;

import lk.ijse.Model.MemberDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDao {
    ArrayList<Member> getSixData() throws SQLException, ClassNotFoundException;
}
