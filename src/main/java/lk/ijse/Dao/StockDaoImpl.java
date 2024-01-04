package lk.ijse.Dao;

import lk.ijse.TM.bookTm;
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

    @Override
    public boolean Save(Stock dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES (?,?,?,?,?,?,?)",
                dto.getCode(),dto.getType(),dto.getDate(),dto.getDomain(),dto.getDomain_Name(),dto.getMember(),dto.getCount());
    }

    @Override
    public boolean update(Stock dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET Domain = ?, Domain_name = ?, Type = ?, Date = ?, Member = ?, Count = ? WHERE code = ?",
                dto.getType(),dto.getDate(),dto.getDomain(),dto.getDomain_Name(),dto.getMember(),dto.getCount(),dto.getCode());
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public Stock getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock WHERE code = ?", id);

        Stock dto = null;

        if (resultSet.next()) {
            dto = new Stock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(7)
            );
        }
        System.out.println(dto);
        return dto;
    }
    @Override
    public boolean update(int count, int qty, String itemCode) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET Count = ? WHERE code = ?",(qty-count),itemCode);
    }

    private boolean updateQty(bookTm cartTm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock set Count = ? WHERE code = ?",cartTm.getCount(),cartTm.getItemCode());
    }
}
