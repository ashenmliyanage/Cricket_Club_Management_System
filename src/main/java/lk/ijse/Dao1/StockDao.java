package lk.ijse.Dao1;

import lk.ijse.Model.StockDto;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao {
    ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException;
}
