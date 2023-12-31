package lk.ijse.dao.impl;

import lk.ijse.Model.MemberDto;
import lk.ijse.Model.StockDto;
import lk.ijse.TM.bookTm;
import lk.ijse.dao.StockManageDao;
import lk.ijse.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockManageDaoimpl implements StockManageDao{
    @Override
    public int[] getItemCounts() throws SQLException, ClassNotFoundException {
        ArrayList<StockDto> arrayList = getAll();

        int[] count = new int[6];

        for (int i = 0; i < arrayList.size(); i++) {
            switch (arrayList.get(i).getType()){
                case "Bat":
                    count[0]++;
                    break;
                case "Ball":
                    count[1]++;
                    break;
                case "Wicket Stamp":
                    count[2]++;
                    break;
                case "Helmet":
                    count[3]++;
                    break;
                case "Pad":
                    count[4]++;
                    break;
                case "Gloves":
                    count[5]++;
                    break;
            }
        }
        return count;
    }

    @Override
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock");

        ArrayList<StockDto> dto = new ArrayList<>();
        while (resultSet.next()){
            dto.add(
                    new StockDto(
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
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public boolean Save(StockDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES (?,?,?,?,?,?,?)",
                dto.getCode(),dto.getType(),dto.getDate(),dto.getDomain(),dto.getDomain_Name(),dto.getMember(),dto.getCount());
    }

    @Override
    public StockDto getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock WHERE code = ?", id);

        StockDto dto = null;

        if (resultSet.next()) {
            dto = new StockDto(
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
    public boolean update(StockDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET Domain = ?, Domain_name = ?, Type = ?, Date = ?, Member = ?, Count = ? WHERE code = ?",
                dto.getType(),dto.getDate(),dto.getDomain(),dto.getDomain_Name(),dto.getMember(),dto.getCount(),dto.getCode());
    }

    public boolean update(List<bookTm> tmList) throws SQLException, ClassNotFoundException {
        for (bookTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(bookTm cartTm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock set Count = ? WHERE code = ?",cartTm.getCount(),cartTm.getItemCode());
    }

    @Override
    public boolean Delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }
}
