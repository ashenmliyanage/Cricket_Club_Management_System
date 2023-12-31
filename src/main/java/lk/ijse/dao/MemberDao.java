package lk.ijse.dao;

import lk.ijse.Model.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDao extends CrudDao<MemberDto>{
    ArrayList<MemberDto> getSixData() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    int getMemberCount() throws SQLException, ClassNotFoundException;
}
