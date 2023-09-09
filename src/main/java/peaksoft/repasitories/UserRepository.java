package peaksoft.repasitories;


import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getAdministratorByEmail(String email);


    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);
}