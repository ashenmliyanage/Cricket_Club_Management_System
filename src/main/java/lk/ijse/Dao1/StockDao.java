package lk.ijse.Dao1;

import lk.ijse.Model.StockDto;
import lk.ijse.TM.bookTm;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StockDao {
    ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException;

    boolean Save(Stock dto) throws SQLException, ClassNotFoundException;

    boolean update(Stock dto) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    Stock getData(String id) throws SQLException, ClassNotFoundException;

    boolean update(int count, int qty, String itemCode) throws SQLException, ClassNotFoundException;
}
