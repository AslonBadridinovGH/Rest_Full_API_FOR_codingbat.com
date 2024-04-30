package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
