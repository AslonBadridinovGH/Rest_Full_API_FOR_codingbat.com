package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Answer;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
