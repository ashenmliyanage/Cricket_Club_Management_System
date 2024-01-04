package lk.ijse.Dao;

import lk.ijse.Dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static  DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        Member,Order,OrderDetails,Stock,User
    }

    public SuperDAO getDAO(DAOType type){
        switch (type){
            case Member:
                return new MemberDaoImpl();
            case Order:
                return new OrderDaoImpl();
            case OrderDetails:
                return new OrderDetailDaoImpl();
            case Stock:
                return new StockDaoImpl();
            case User:
                return new UserDaoImpl();
            default:
                return null;
        }
    }
}
