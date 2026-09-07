package Service.Custom;

import Service.SuperService;
import model.entity.Item;

import java.util.List;

public interface ItemService extends SuperService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(String id);
    Item searchItemById(String id);
    List<Item> getAllItems();
}
