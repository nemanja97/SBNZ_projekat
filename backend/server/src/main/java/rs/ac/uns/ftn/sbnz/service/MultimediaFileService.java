package rs.ac.uns.ftn.sbnz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.sbnz.models.MultimediaFile;

public interface MultimediaFileService {

    MultimediaFile findById(Long id);

    Page<MultimediaFile> findAll(Pageable page);

    void save(MultimediaFile multimediaFile);

    void delete(MultimediaFile multimediaFile);
}
