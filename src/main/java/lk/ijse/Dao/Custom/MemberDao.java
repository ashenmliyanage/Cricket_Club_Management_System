package lk.ijse.Dao.Custom;

import lk.ijse.entity.Member;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDao extends CrudUtil<Member> {
    ArrayList<Member> getSixData() throws SQLException, ClassNotFoundException;

    int getMemberCount() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
}
