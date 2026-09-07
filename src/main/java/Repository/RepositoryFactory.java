package Repository;

import Repository.Custom.CustomerRepository;
import Repository.Custom.Impl.CustomerRepositoryImpl;
import Repository.Custom.Impl.ItemRepositoryImpl;
import Repository.Custom.Impl.OrderRepositoryImpl;
import Service.ServiceFactory;
import utill.RepositoryType;

public class RepositoryFactory {

    private static RepositoryFactory instance;

    private RepositoryFactory() {}

    public static RepositoryFactory getInstance() {
        return instance==null?instance=new RepositoryFactory():instance;
    }

    public  static <T extends SuperRepository>T getRepositorytype(RepositoryType repoType){
        switch (repoType){
            case CUSTOMER:return(T) new CustomerRepositoryImpl();
            case ITEM:return (T)new ItemRepositoryImpl();
            case ORDER:return (T)new OrderRepositoryImpl();

        }
        return null;
    }

}
