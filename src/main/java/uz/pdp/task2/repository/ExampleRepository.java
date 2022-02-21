package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Example;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
}
