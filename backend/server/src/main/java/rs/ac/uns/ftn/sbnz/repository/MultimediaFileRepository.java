package rs.ac.uns.ftn.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbnz.models.MultimediaFile;

public interface MultimediaFileRepository extends JpaRepository<MultimediaFile, Long> {
}
