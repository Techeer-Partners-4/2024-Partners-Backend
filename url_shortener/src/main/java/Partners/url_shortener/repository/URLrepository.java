package Partners.url_shortener.repository;

import Partners.url_shortener.domain.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLrepository extends JpaRepository<URL, Long> {
}
