package Repository.Custom.Impl;

import Repository.Custom.ItemRepository;
import db.DBConnection;
import model.entity.Item;
import utill.CrudUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean create(Item item) {
        try{


            return CrudUtill.execute("INSERT INTO item VALUES(?,?,?,?,?)",
                    item.getId(),
                    item.getDescription(),
                    item.getPacksize(),
                    item.getUnitprice(),
                    item.getQuantity()
            );



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
           return CrudUtill.execute("DELETE FROM item WHERE ItemCode=?",id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item getbyId(String id) {
        try {

            ResultSet resultSet = CrudUtill.execute("SELECT * FROM item WHERE ItemCode=?",id);

            Item item = new Item();

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
            ResultSet rs=CrudUtill.execute("SELECT * FROM item");



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


        
    }
}
