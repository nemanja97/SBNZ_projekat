package rs.ac.uns.ftn.sbnz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.models.MultimediaFile;
import rs.ac.uns.ftn.sbnz.repository.MultimediaFileRepository;
import rs.ac.uns.ftn.sbnz.service.MultimediaFileService;

@Service
public class MultimediaFileServiceImpl implements MultimediaFileService {

    @Autowired
    private MultimediaFileRepository multimediaFileRepository;

    @Override
    public MultimediaFile findById(Long id) {
        return multimediaFileRepository.findById(id).orElse(null);
    }

    @Override
    public Page<MultimediaFile> findAll(Pageable page) {
        return multimediaFileRepository.findAll(page);
    }

    @Override
    public void save(MultimediaFile multimediaFile) {
        multimediaFileRepository.save(multimediaFile);
    }

    @Override
    public void delete(MultimediaFile multimediaFile) {
        multimediaFileRepository.delete(multimediaFile);
    }
}
