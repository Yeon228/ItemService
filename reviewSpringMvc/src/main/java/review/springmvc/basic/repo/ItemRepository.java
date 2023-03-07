package review.springmvc.basic.repo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import review.springmvc.data.Item;

import java.util.*;
import java.util.logging.Logger;

@Repository
@Slf4j
public class ItemRepository {
    private final Map<Integer, Item> repository = new HashMap<>();
    private int count = 0;

    public Item save(Item item){
        log.info("Saved Item");
        item.setId(++count);
        repository.put(item.getId(),item);
        return item;
    }

    public Item findById(Integer id){
        return repository.get(id);
    }

    public void update(Integer id, Item newItem){
        Item item = repository.get(id);
        log.info(item + " is updated");
        repository.remove(id);
        repository.put(id,newItem);
    }

    public void removeAll(){
        log.warn("Repository's Items was cleared");
        repository.clear();
    }

    public boolean buy(Integer itemId){
        Item item = findById(itemId);
        if(item.getQuantity() == 0){
            log.warn(item.getItemName() + " is all sold");
            return false;
        }
        item.setQuantity(item.getQuantity()-1);
        log.info("Buy " + item.getItemName() + " Now item's quantity is " + item.getQuantity());
        return true;
    }

    public List<Item> getAll(){
        log.info("Get all repository's item");
        return new ArrayList<>(repository.values());
    }
}

