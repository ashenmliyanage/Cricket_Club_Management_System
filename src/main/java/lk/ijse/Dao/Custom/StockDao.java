package lk.ijse.Dao.Custom;

import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao {
    ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException;

    boolean Save(Stock dto) throws SQLException, ClassNotFoundException;

    boolean update(Stock dto) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    Stock getData(String id) throws SQLException, ClassNotFoundException;

    boolean update(int count, int qty, String itemCode) throws SQLException, ClassNotFoundException;
}
