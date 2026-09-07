package Repository.Custom.Impl;

import Repository.Custom.CustomerRepository;
import db.DBConnection;
import model.entity.Customer;

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
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getTitle());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, String.valueOf(customer.getDob()));
            preparedStatement.setDouble(5, customer.getSalary());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9,customer.getPostalcode());

            if(preparedStatement.executeUpdate()>0){
                return true;

            }else{
                return false;
            }

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
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from customer where CustID=?");
            preparedStatement.setString(1, id);
            if(preparedStatement.executeUpdate()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getbyId(String id) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from customer where CustID = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
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
            Connection connection = DBConnection.getInstance().getConnection();


            System.out.println(DBConnection.getInstance().getConnection());
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM Customer");



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
