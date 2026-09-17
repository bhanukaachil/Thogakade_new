package Repository.Custom.Impl;

import Repository.Custom.CustomerRepository;
import db.DBConnection;
import model.entity.Customer;
import utill.CrudUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) {
        try {


             return CrudUtill.execute("insert into customer values(?,?,?,?,?,?,?,?,?)",
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalcode()
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            return CrudUtill.execute("delete from customer where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getbyId(String id) {
        try {

            ResultSet rs =CrudUtill.execute("select * from customer where CustID = ?", id);
            rs.next();
            Customer customer = new Customer();
            customer.setId(rs.getString(1));
            customer.setTitle(rs.getString(2));
            customer.setName(rs.getString(3));
            customer.setDob(rs.getDate(4));
            customer.setSalary(rs.getDouble(5));
            customer.setAddress(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setProvince(rs.getString(8));
            customer.setPostalcode(rs.getString(9));

            return customer;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customerTMArrayList = new ArrayList<>();

        try {

           ResultSet rs= CrudUtill.execute("SELECT * FROM Customer");


            while(rs.next()){
                customerTMArrayList.add(new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                ));


            }

            return   customerTMArrayList;




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
