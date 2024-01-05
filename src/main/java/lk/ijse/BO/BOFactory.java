package lk.ijse.BO;

import lk.ijse.BO.custom.impl.*;

public class BOFactory {
    public static BOFactory factory;

    private BOFactory(){}
    public static BOFactory getInstance(){
        return (factory == null) ? factory = new BOFactory() : factory;
    }

    public enum BOType{
        Login,CreateAccount,Dashboard,Member,Stock,Book,Setting,Forget
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case Login:
                return new LoginBoImpl();
            case CreateAccount:
                return new CreateAccountBoImpl();
            case Dashboard:
                return new DashboardBoImpl();
            case Member:
                return new MemberBoImpl();
            case Stock:
                return new StockManageBoImpl();
            case Book:
                return new BookBoImpl();
            case Setting:
                return new SettingBoImpl();
            case Forget:
                return new ForgetPasswordBoImpl();
            default:
                return null;
        }
    }
}
