package rs.ac.uns.ftn.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbnz.models.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
