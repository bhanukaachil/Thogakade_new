package Repository.Custom;

import Repository.CrudRepository;
import Repository.SuperRepository;
import model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,String> {
}
