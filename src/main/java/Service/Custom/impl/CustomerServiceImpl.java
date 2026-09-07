package Service.Custom.impl;

import Repository.Custom.CustomerRepository;
import Repository.RepositoryFactory;
import Service.Custom.CustomerService;

import model.entity.Customer;
import utill.RepositoryType;

import java.util.List;




public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository=RepositoryFactory.getInstance().getRepositorytype(RepositoryType.CUSTOMER);

    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepository.create(customer);

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return customerRepository.delete(id);

    }

    @Override
    public Customer SearchCustomerById(String id) {
       return customerRepository.getbyId(id);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }
}
