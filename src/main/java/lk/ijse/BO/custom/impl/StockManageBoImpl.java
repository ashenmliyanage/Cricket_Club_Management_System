package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.StockManageBo;
import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.Dao.Custom.impl.MemberDaoImpl;
import lk.ijse.Dao.Custom.StockDao;
import lk.ijse.Dao.Custom.impl.StockDaoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.Model.StockDto;
import lk.ijse.entity.Stock;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageBoImpl implements StockManageBo {
    StockDao stockDao = new StockDaoImpl();

    MemberDao memberDao = new MemberDaoImpl();

    @Override
    public boolean Save(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDao.Save(new Stock(
                dto.getCode(),
                dto.getDomain(),
                dto.getDomain_Name(),
                dto.getType(),
                dto.getDate(),
                dto.getMember(),
                dto.getCount()
        ));
    }

    @Override
    public boolean update(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDao.update(new Stock(
                dto.getCode(),
                dto.getDomain(),
                dto.getDomain_Name(),
                dto.getType(),
                dto.getDate(),
                dto.getMember(),
                dto.getCount()
        ));
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return stockDao.generateId(colum,table,type);
    }

    @Override
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> all = stockDao.getAll();
        ArrayList<StockDto> dto = new ArrayList<>();

        for (Stock stock : all) {
            dto.add(new StockDto(
                    stock.getCode(),
                    stock.getDomain(),
                    stock.getDomain_Name(),
                    stock.getType(),
                    stock.getDate(),
                    stock.getMember(),
                    stock.getCount()
            ));
        }
        return dto;
    }
    @Override
    public StockDto getData(String id) throws SQLException, ClassNotFoundException {
        Stock data = stockDao.getData(id);
        return new StockDto(
                data.getCode(),
                data.getDomain(),
                data.getDomain_Name(),
                data.getType(),
                data.getDate(),
                data.getMember(),
                data.getCount()
        );
    }

    @Override
    public ArrayList<MemberDto> geMembertAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM member");
        ArrayList<MemberDto> dtos = new ArrayList<>();

        while (resultSet.next()){
            dtos.add(new MemberDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getBinaryStream(8)
            ));
        }
        return dtos;
    }

}