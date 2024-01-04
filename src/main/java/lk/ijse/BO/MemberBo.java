package lk.ijse.BO;

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

    boolean Delete(String name) throws SQLException, ClassNotFoundException;
}
