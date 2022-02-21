package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
