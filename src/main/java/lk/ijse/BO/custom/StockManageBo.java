package lk.ijse.BO.custom;

import lk.ijse.Model.MemberDto;
import lk.ijse.Model.StockDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockManageBo {
    boolean Save(StockDto dto) throws SQLException, ClassNotFoundException;

    boolean update(StockDto dto) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException;

    StockDto getData(String id) throws SQLException, ClassNotFoundException;

    ArrayList<MemberDto> geMembertAll() throws SQLException, ClassNotFoundException;
}
