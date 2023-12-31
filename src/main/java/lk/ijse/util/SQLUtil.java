package lk.ijse.util;

import lk.ijse.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... arg) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        for (int i = 0; i < arg.length; i++) {
            statement.setObject((i + 1), arg[i]);
        }

        if (sql.trim().toUpperCase().startsWith("SELECT")){
            return (T) statement.executeQuery();
        }else {
            return(T)(Boolean)(statement.executeUpdate() > 0);
        }
    }

    public static String genarate(String colum, String table,String type) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT "+colum+" FROM "+table+" ORDER BY "+colum+" DESC LIMIT 1");

        String lastId = null;
        if (resultSet.next()){
            lastId = resultSet.getString(1);
        }
        if (lastId == null){
            return type+"1";
        }
        String[] split = lastId.split(type);
        int index = Integer.parseInt(split[1]);
        index++;
        System.out.println(type+index);
        return type+ index;
    }
}
