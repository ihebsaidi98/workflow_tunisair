package tn.tunisair.workfow.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tunisair.workfow.Entities.Changement;

@Repository
public interface ChangementRepository extends JpaRepository<Changement, Integer> {
}
