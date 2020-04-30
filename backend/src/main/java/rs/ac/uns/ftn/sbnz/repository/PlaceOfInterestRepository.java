package rs.ac.uns.ftn.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;

public interface PlaceOfInterestRepository extends JpaRepository<PlaceOfInterest, Long> {
}
