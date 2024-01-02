package lk.ijse.dao.impl;

import lk.ijse.Model.BookDto;
import lk.ijse.dao.BookDao;
import lk.ijse.dao.OrderDao;
import lk.ijse.dao.StockManageDao;
import lk.ijse.util.SQLUtil;
import lk.ijse.util.TransactionUtil;

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
