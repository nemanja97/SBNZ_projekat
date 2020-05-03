package rs.ac.uns.ftn.sbnz.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, Long propertyId);

    Resource loadFileAsResource(String fileName);

    void deleteFile(String fileName, Long id);
}
