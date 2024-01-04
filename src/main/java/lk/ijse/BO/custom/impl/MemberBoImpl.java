package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.MemberBo;
import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.Dao.Custom.impl.MemberDaoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBoImpl implements MemberBo {

    MemberDao memberDao = new MemberDaoImpl();
    @Override
    public ArrayList<MemberDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Member> data = memberDao.getAll();

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
    public int getMemberCount() throws SQLException, ClassNotFoundException {
        return memberDao.getMemberCount();
    }
    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        return memberDao.getAllName();
    }
    @Override
    public MemberDto getData(String id) throws SQLException, ClassNotFoundException {
        Member data = memberDao.getData(id);

        return new MemberDto(
                data.getMember_id(),
                data.getFull_name(),
                data.getPosition(),
                data.getBod(),
                data.getAge(),
                data.getEmail(),
                data.getAddress(),
                data.getImage()
        );
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return memberDao.generateId(colum,table,type);
    }
    @Override
    public boolean Save(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDao.Save(new Member(
                dto.getMember_id(),
                dto.getFull_name(),
                dto.getPosition(),
                dto.getBod(),
                dto.getAge(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getImage()
        ));
    }

    @Override
    public boolean update(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDao.update(new Member(
                dto.getMember_id(),
                dto.getFull_name(),
                dto.getPosition(),
                dto.getBod(),
                dto.getAge(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getImage()
        ));
    }

    @Override
    public boolean Delete(String name) throws SQLException, ClassNotFoundException {
        return memberDao.Delete(name);
    }
}
