package Service.Custom.impl;

import Service.Custom.ItemService;
import db.DBConnection;
import model.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {

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
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }

    @Override
    public Item searchItemById(String id) {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }
}
