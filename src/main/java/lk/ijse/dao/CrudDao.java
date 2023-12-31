package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<Ashen> {
    ArrayList<Ashen> getAll() throws SQLException, ClassNotFoundException;
    String generateId(String colum, String table,String type) throws SQLException, ClassNotFoundException;
    boolean Save(Ashen dto) throws SQLException, ClassNotFoundException;

    Ashen getData(String id) throws SQLException, ClassNotFoundException;

    boolean update(Ashen dto) throws SQLException, ClassNotFoundException;

    boolean Delete(String name) throws SQLException, ClassNotFoundException;
}
