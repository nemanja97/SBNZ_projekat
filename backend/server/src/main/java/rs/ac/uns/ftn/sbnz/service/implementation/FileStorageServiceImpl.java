package rs.ac.uns.ftn.sbnz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.sbnz.configuration.properties.FileStorageProperties;
import rs.ac.uns.ftn.sbnz.exception.FileStorageException;
import rs.ac.uns.ftn.sbnz.repository.MultimediaFileRepository;
import rs.ac.uns.ftn.sbnz.service.FileStorageService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private MultimediaFileRepository multimediaFileRepository;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file, Long propertyId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileName = propertyId.toString() + "_" + new Date().getTime() + "_" + fileName;

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence.");
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException(String.format("Could not store file %s.", fileName), ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileStorageException("File not found " + fileName, ex);
        }
    }

    @Override
    public void deleteFile(String fileName, Long id) {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        File file = new File(filePath.toUri());
        if(file.exists()) {
            if(file.delete()) {
                multimediaFileRepository.deleteById(id);
            }
        } else {
            throw new FileStorageException("File not found " + fileName);
        }
    }
}
