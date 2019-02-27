package conglin.serendipity.repository;

import conglin.serendipity.domain.Serendipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerendipperRepository extends JpaRepository<Serendipper, Long> {
    Serendipper findByEmail(String email);
    Serendipper findByUsername(String username);

}