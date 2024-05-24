package Partners.url_shortener.repository;

import Partners.url_shortener.domain.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.Optional;

public interface URLrepository extends JpaRepository<URL, Long> {
    Optional<URL> findByOriginalURL(String originalURL);
}
