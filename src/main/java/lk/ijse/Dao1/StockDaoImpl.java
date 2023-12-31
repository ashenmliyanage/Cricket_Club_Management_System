package lk.ijse.Dao1;

import lk.ijse.Model.StockDto;
import lk.ijse.entity.Stock;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDaoImpl implements StockDao{
    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock");

        ArrayList<Stock> dto = new ArrayList<>();
        while (resultSet.next()){
            dto.add(
                    new Stock(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7)
                    )
            );
        }
        return dto;
    }
}
