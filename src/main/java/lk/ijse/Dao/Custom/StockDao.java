package lk.ijse.Dao.Custom;

import lk.ijse.entity.Stock;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao extends CrudUtil<Stock> {
    boolean update(int count, int qty, String itemCode) throws SQLException, ClassNotFoundException;
}
