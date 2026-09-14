package Service.Custom.impl;


import Repository.Custom.ItemRepository;
import Repository.RepositoryFactory;
import Service.Custom.ItemService;

import model.entity.Item;
import utill.RepositoryType;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository=RepositoryFactory.getInstance().getRepositorytype(RepositoryType.ITEM);

    @Override
    public boolean addItem(Item item) {return  itemRepository.create(item);}

    @Override
    public boolean updateItem(Item item) {
        return itemRepository.update(item);
    }

    @Override
    public boolean deleteItem(String id) {return itemRepository.delete(id);}

    @Override
    public Item searchItemById(String id) {
       return itemRepository.getbyId(id);
    }

    @Override
    public List<Item> getAllItems() {return itemRepository.getAll();}
}
