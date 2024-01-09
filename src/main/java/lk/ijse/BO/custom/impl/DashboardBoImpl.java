package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.DashboardBo;
import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Dao.Custom.StockDao;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Model.MemberDto;
import lk.ijse.entity.Member;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardBoImpl implements DashboardBo {

    MemberDao memberDao = (MemberDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Member);
    StockDao stockDao = (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Stock);
    OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Order);

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
    @Override
    public ArrayList<Integer> getChartData() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = orderDao.ChartData();
        int i = 0;
        ArrayList<Integer> num = new ArrayList<>();
        Map<String,Integer> valueCount = countEqualValues(list);

        for (Map.Entry<String, Integer> entry : valueCount.entrySet()) {
            System.out.println();
            System.out.println("Value: " + entry.getKey() + ", Count: " + entry.getValue());
            num.add(entry.getValue());
            System.out.println(list.get(i++));
        }

        return num;
    }

    private static Map<String, Integer> countEqualValues(ArrayList<String> strings) {
        Map<String, Integer> valueCounts = new HashMap<>();

        for (String str : strings) {
            // Increment the count for the current value in the map
            valueCounts.put(str, valueCounts.getOrDefault(str, 0) + 1);
        }

        return valueCounts;
    }
}
