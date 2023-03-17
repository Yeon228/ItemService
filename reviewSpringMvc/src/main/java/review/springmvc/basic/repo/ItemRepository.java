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

@Slf4j
public class ItemRepository {
    private final MariaDbDataSource dataSource = new MariaDbDataSource();
    private JdbcTemplate jdbcTemplate;
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
        String sql = "SELECT COUNT(*) FROM item";
        int itemId = jdbcTemplate.queryForObject(sql, Integer.class) + 1;
        item.setId(itemId);
        repository.put(itemId,item);
        insertItem(item);
        return item;
    }


    public Item findById(Integer id){
        return repository.get(id);
    }

    public void update(Item item){
        String sql = "UPDATE item SET itemName = '"+ item.getItemName() + "', price = " + item.getPrice()
                + ", quantity = " + item.getQuantity() + " WHERE Id = " + item.getId();
        jdbcTemplate.update(sql);
    }

    public void removeAll(){
        log.warn("Repository's Items was cleared");
        repository.clear();
    }

    public void insertItem(Item item){
        String sql = "INSERT INTO item (itemName, id, price, quantity) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getItemName(), item.getId(), item.getPrice(), item.getQuantity());
        log.info("Insert item at database");
    }

    public void delete(Integer itemId){
        String sql = "DELETE FROM item WHERE ID = " + itemId.toString();
        jdbcTemplate.update(sql);
        log.info("Remove item at database");
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
        repository.clear();
        jdbcTemplate.query("SELECT * FROM practice.item", rs -> {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setItemName(rs.getString("itemName"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            repository.put(item.getId(), item);
        });
        log.info("Get all repository's item");
        return new ArrayList<>(repository.values());
    }
}

