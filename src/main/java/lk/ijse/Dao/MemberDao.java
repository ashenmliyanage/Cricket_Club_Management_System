package lk.ijse.Dao;

import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDao {
    ArrayList<Member> getSixData() throws SQLException, ClassNotFoundException;

    int getMemberCount() throws SQLException, ClassNotFoundException;

    ArrayList<Member> getAll() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;

    Member getData(String id) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(Member dto) throws SQLException, ClassNotFoundException;

    boolean update(Member dto) throws SQLException, ClassNotFoundException;

    boolean Delete(String name) throws SQLException, ClassNotFoundException;
}
