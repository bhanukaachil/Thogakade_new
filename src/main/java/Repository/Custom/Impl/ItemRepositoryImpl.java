package Repository.Custom.Impl;

import Repository.Custom.ItemRepository;
import db.DBConnection;
import model.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean create(Item item) {
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO item VALUES(?,?,?,?,?)")) {

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getPacksize());
            preparedStatement.setDouble(4, item.getUnitprice());
            preparedStatement.setDouble(5, item.getQuantity());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM item WHERE ItemCode=?");
            preparedStatement.setString(1, id);

            if(preparedStatement.executeUpdate() > 0){
                return true;

            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item getbyId(String id) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM item WHERE ItemCode=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Item item = new Item();

            System.out.println(item.getDescription());
            resultSet.next();
            item.setId(resultSet.getString(1));
            item.setDescription(resultSet.getString(2));
            item.setPacksize(resultSet.getString(3));
            item.setUnitprice(resultSet.getDouble(4));
            item.setQuantity(resultSet.getInt(5));



            return item;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAll() {

        ArrayList<Item> ItemTMArrayList = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();


            System.out.println(DBConnection.getInstance().getConnection());
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM item");



            while(rs.next()){
                ItemTMArrayList.add(new Item(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)

                ));


            }

            return   ItemTMArrayList;




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //return List.of();
    }
}
