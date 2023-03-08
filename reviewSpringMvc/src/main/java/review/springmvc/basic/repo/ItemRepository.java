package review.springmvc.basic.repo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import review.springmvc.data.Item;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@Repository
@Slf4j
public class ItemRepository {
    private JdbcTemplate jdbcTemplate;
    private MariaDbDataSource dataSource = new MariaDbDataSource();
    private final Map<Integer, Item> repository = new HashMap<>();
    private int count = 0;
    public ItemRepository() throws SQLException {
        dataSource.setUrl("jdbc:mariadb://152.67.198.30:3306/practice");
        dataSource.setUser("doyeon");
        dataSource.setPassword("1q2w3e4r");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Item save(Item item){
        log.info("Saved Item");
        item.setId(++count);
        repository.put(item.getId(),item);
        insertItem(item);
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

    public void insertItem(Item item){
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO item (itemName, id, price, quantity) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getItemName(), item.getId(), item.getPrice(), item.getQuantity());
        log.info("update done?");

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

