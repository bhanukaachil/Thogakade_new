package Service;

import Service.Custom.impl.CustomerServiceImpl;
import Service.Custom.impl.ItemServiceImpl;
import Service.Custom.impl.OrderServiceImpl;
import utill.ServiceType;

import static javafx.scene.input.KeyCode.T;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }


    public <T extends SuperService>T getServiceType(ServiceType servicetype){

        switch (servicetype){
            case CUSTOMER:return(T)new CustomerServiceImpl();
            case ITEM:return(T) new ItemServiceImpl();
            case ORDER:return(T)new OrderServiceImpl();
            default:return null;
        }

    }
}
