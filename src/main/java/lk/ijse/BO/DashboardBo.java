package lk.ijse.BO;

import lk.ijse.Model.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardBo {
    ArrayList<MemberDto> getSixData() throws SQLException, ClassNotFoundException;

    int[] getCount() throws SQLException, ClassNotFoundException;
}
