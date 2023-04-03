package refactoring.semiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.semiproject.domain.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
