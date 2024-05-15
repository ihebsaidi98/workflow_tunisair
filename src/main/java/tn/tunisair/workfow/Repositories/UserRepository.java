package tn.tunisair.workfow.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.tunisair.workfow.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {


    Optional<User> findByEmail(String email);
}
