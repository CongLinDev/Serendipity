package conglin.serendipity.repository;

import conglin.serendipity.domain.Serendimsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerendimsgRepository extends JpaRepository<Serendimsg, Long>{

}