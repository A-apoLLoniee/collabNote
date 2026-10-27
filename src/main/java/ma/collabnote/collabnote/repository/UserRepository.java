package ma.collabnote.collabnote.repository;

import ma.collabnote.collabnote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
