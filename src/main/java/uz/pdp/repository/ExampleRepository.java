package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Example;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
}
