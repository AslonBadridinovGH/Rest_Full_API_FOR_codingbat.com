package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Answer;


public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
