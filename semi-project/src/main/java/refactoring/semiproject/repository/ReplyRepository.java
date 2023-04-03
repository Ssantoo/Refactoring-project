package refactoring.semiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.semiproject.domain.entity.Photo;
import refactoring.semiproject.domain.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {


}
