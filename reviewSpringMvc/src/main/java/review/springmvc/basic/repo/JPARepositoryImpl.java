package review.springmvc.basic.repo;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import review.springmvc.data.Item;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JPARepositoryImpl {
    private final JPAItemRepository jpaItemRepository;

    public void insert(Item item){
        jpaItemRepository.save(item);
    }

    public Optional<Item> findById(Integer id){
        return jpaItemRepository.findById(id);
    }

    public Item getById(Integer id){
        return jpaItemRepository.getReferenceById(id);
    }
    public void delete(int id){
        Optional<Item> itemOptional = findById(id);
        if(itemOptional.isEmpty()){
            log.error("WARNING!! THERE IS NO ITEM, INPUT ID : " + id);
        }
        else {
            log.info("SUCCESS TO DELETE, INPUT ID : " + id);
            jpaItemRepository.deleteById(id);
        }
    }

    public void buy(Integer id){
        Item item = jpaItemRepository.getReferenceById(id);
        if(item.getQuantity() <= 0){
            log.warn("THERE IS NO ITEM, INPUT ID : " + id);
        }
        else {
            int quantity = item.getQuantity();
            item.setQuantity(--quantity);
            jpaItemRepository.save(item);
            log.info("success to buy item");
        }
    }

    public List<Item> getAll(){
        return jpaItemRepository.findAll();
    }
}
