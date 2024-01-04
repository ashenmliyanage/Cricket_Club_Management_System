package lk.ijse.BO.custom;

import lk.ijse.Model.MemberDto;
import lk.ijse.Model.OrderDto;
import lk.ijse.Model.StockDto;

import java.sql.SQLException;
import java.util.List;

public interface BookBo {
    boolean Save(OrderDto dto) throws SQLException, ClassNotFoundException;

    StockDto getData(String Id) throws SQLException, ClassNotFoundException;

    List<StockDto> getAll() throws SQLException, ClassNotFoundException;

    MemberDto getMData(String Id) throws SQLException, ClassNotFoundException;
}
