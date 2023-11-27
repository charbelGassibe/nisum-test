package cl.charbel.nisumtest.persistence;

import cl.charbel.nisumtest.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);
}
