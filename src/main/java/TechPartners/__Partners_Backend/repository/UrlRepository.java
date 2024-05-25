package TechPartners.__Partners_Backend.repository;

import TechPartners.__Partners_Backend.domain.Url;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByHashAndDeleted(String hash, boolean deleted);

    List<Url> findAllByDeleted(boolean deleted);
}
