package lk.ijse.dao.impl;

import lk.ijse.Model.BookDto;
import lk.ijse.Model.OrderDto;
import lk.ijse.Model.StockDto;
import lk.ijse.dao.BookDao;
import lk.ijse.dao.OrderDao;
import lk.ijse.dao.StockManageDao;
import lk.ijse.util.SQLUtil;
import lk.ijse.util.TransactionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoimpl implements BookDao {
    @Override
    public ArrayList<BookDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum, table, type);
    }

    @Override
    public boolean Save(BookDto dto) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();
            OrderDao orderDao = new OrderDaoImpl();
            if (orderDao.Save(new OrderDto(dto.getBook_id(),dto.getMember_Id(),dto.getLocalDate(), (int) dto.getCount()))) {
                StockManageDao stockManageDao = new StockManageDaoimpl();
                boolean isUpdate = stockManageDao.update(dto.getTmList());
                if (isUpdate){
                    return true;
                }
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
        } catch (ClassNotFoundException e) {
            TransactionUtil.endTransaction();
        }
        return false;
    }
    @Override
    public BookDto getData(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(BookDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }
}
