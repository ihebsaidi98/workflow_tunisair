package tn.tunisair.workfow.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.tunisair.workfow.Entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role,Long> {

    Optional<Role> findByName(String role);

}
