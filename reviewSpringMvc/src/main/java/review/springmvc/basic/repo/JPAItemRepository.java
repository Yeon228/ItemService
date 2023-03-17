package review.springmvc.basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import review.springmvc.data.Item;

public interface JPAItemRepository extends JpaRepository<Item, Integer> {

}
