package lk.ijse.Dao1;

import lk.ijse.Model.MemberDto;
import lk.ijse.entity.Member;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDaoImpl implements MemberDao{
    @Override
    public ArrayList<Member> getSixData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select * from member order by Member_ID desc");

        ArrayList<Member> dto = new ArrayList<>();
        while (resultSet.next()){
            dto.add(new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getAsciiStream(8)
            ));
        }
        return dto;
    }
}
