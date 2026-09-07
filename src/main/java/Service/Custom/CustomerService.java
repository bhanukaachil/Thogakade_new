package Service.Custom;

import Service.SuperService;
import model.entity.Customer;

import java.util.List;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    Customer SearchCustomerById(String id);
    List<Customer> getAllCustomers();
}
