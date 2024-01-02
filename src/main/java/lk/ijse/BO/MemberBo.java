package lk.ijse.BO;

import lk.ijse.Dao1.MemberDao;
import lk.ijse.Dao1.MemberDaoImpl;
import lk.ijse.Model.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBo {

    ArrayList<MemberDto> getAll() throws SQLException, ClassNotFoundException;

    int getMemberCount() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;

    MemberDto getData(String id) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(MemberDto dto) throws SQLException, ClassNotFoundException;

    boolean update(MemberDto dto) throws SQLException, ClassNotFoundException;
}
