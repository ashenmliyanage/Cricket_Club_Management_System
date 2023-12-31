package lk.ijse.Dao.Custom.impl;

import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Member;
import lk.ijse.util.SQLUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

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

    @Override
    public int getMemberCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM member");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean update(Member dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE member SET Full_Name = ?, Position = ?, bod = ?, Age = ?, Email = ?, Address = ?, Image = ? WHERE Member_ID=?",
                dto.getFull_name(),
                dto.getPosition(),
                dto.getBod(),
                dto.getAge(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getImage(),
                dto.getMember_id());
    }

    @Override
    public ArrayList<Member> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM member");
        ArrayList<Member> dtos = new ArrayList<>();

        while (resultSet.next()){
            dtos.add(new Member(
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

    @Override
    public boolean Save(Member dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO member VALUES (?,?,?,?,?,?,?,?)",
                dto.getMember_id(),dto.getFull_name(),dto.getPosition(),dto.getBod(),dto.getAge(),dto.getEmail(),dto.getAddress(),dto.getImage());
    }

    @Override
    public Member getData(String Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM member WHERE Full_Name = ?", Id);

        Member dto = null;

        if (resultSet.next()) {
            dto = new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getBinaryStream(8)
            );
        }
        System.out.println(dto);
        return dto;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
                return SQLUtil.execute(
                "DELETE FROM member WHERE Full_Name = ?",id
        );
    }

    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Full_Name FROM member");
        ArrayList<String> array = new ArrayList<>();

        while (resultSet.next()){
            array.add(resultSet.getString(1));
        }

        return array;
    }

    @Override
    public ArrayList<Member> geMemberAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM member");
        ArrayList<Member> dtos = new ArrayList<>();

        while (resultSet.next()){
            dtos.add(new Member(
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

    @Override
    public void getReport(JasperDesign load) throws JRException, SQLException, ClassNotFoundException {
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}
