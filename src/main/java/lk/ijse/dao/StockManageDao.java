package lk.ijse.dao;

import lk.ijse.Model.StockDto;
import lk.ijse.TM.bookTm;
import lk.ijse.dao.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface StockManageDao extends CrudDao<StockDto> {
    int[] getItemCounts() throws SQLException, ClassNotFoundException;

    boolean update(List<bookTm> tmList) throws SQLException, ClassNotFoundException;
}
