package lk.ijse.BO.impl;

import lk.ijse.BO.DashboardBo;
import lk.ijse.Dao1.MemberDao;
import lk.ijse.Dao1.MemberDaoImpl;
import lk.ijse.Dao1.StockDao;
import lk.ijse.Dao1.StockDaoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.entity.Member;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBoImpl implements DashboardBo {

    MemberDao memberDao = new MemberDaoImpl();
    StockDao stockDao = new StockDaoImpl();

    @Override
    public ArrayList<MemberDto> getSixData() throws SQLException, ClassNotFoundException {
        ArrayList<Member> data = memberDao.getSixData();

        ArrayList<MemberDto> dtos = new ArrayList<>();

        for (Member dto : data){
            dtos.add(
                    new MemberDto(
                            dto.getMember_id(),
                            dto.getFull_name(),
                            dto.getPosition(),
                            dto.getBod(),
                            dto.getAge(),
                            dto.getEmail(),
                            dto.getAddress(),
                            dto.getImage()
                    )
            );
        }
        return dtos;
    }

    @Override
    public int[] getCount() throws SQLException, ClassNotFoundException {

        ArrayList<Stock> arrayList = stockDao.getAll();

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
}
