package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Model.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardBo extends SuperBO {
    ArrayList<MemberDto> getSixData() throws SQLException, ClassNotFoundException;

    int[] getCount() throws SQLException, ClassNotFoundException;
}
