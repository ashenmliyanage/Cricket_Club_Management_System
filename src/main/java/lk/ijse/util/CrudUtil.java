package lk.ijse.util;

import lk.ijse.Dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudUtil<Ashen> extends SuperDAO {
    boolean update(Ashen dto) throws SQLException, ClassNotFoundException;
    ArrayList<Ashen> getAll() throws SQLException, ClassNotFoundException;
    boolean Save(Ashen dto) throws SQLException, ClassNotFoundException;
    Ashen getData(String Id) throws SQLException, ClassNotFoundException;
    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;
    boolean Delete(String id) throws SQLException, ClassNotFoundException;
}
