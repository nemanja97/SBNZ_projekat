package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.sbnz.models.MultimediaFile;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.service.FileStorageService;
import rs.ac.uns.ftn.sbnz.service.MultimediaFileService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.PropertyDTO;
import rs.ac.uns.ftn.sbnz.web.dto.v1.SmartSearchDTO;
import rs.ac.uns.ftn.sbnz.web.dto.v1.UploadFileResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/properties")
@CrossOrigin
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MultimediaFileService multimediaFileService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PropertyDTO>> getProperties() {
        List<Property> properties = propertyService.getProperties();
        List<PropertyDTO> propertyDTOS = properties.stream().map(PropertyDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/optimal", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyDTO>> getOptimalProperties() {
    	ScoredProperties optimalProperties = propertyService.getOptimalProperties(null);
        List<PropertyDTO> propertyDTOS = optimalProperties.getPropertyWithScores().stream().map
        		(p -> new PropertyDTO(p.getProperty())).collect(Collectors.toList());

        return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable Long id) {
        Optional<Property> property = propertyService.getProperty(id);

        return property.map(value -> new ResponseEntity<>(new PropertyDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/multimedia/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMultimediaFile(@PathVariable Long id, HttpServletRequest request) {
        MultimediaFile multimediaFile = multimediaFileService.findById(id);
        if (multimediaFile == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Resource resource = fileStorageService.loadFileAsResource(multimediaFile.getName());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @RequestMapping(value = "/{id}/multimedia", method = RequestMethod.GET)
    public ResponseEntity<List<MultimediaFile>> getPropertyMultimediaFiles(@PathVariable Long id) {
        Optional<Property> property = propertyService.getProperty(id);
        if (property.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ArrayList<>(property.get().getMultimedia()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> addProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convertToEntity();
        propertyService.addProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/multimedia", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UploadFileResponseDTO> addPropertyMultimediaFile(
            @PathVariable Long id, @RequestPart("file") MultipartFile file) {
        Optional<Property> property = propertyService.getProperty(id);
        if (property.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String mimeType = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
        if(!mimeType.equals("image/png") && !mimeType.equals("image/jpeg") && !mimeType.equals("video/mp4")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String fileName = fileStorageService.storeFile(file, id);

        MultimediaFile multimediaFile = new MultimediaFile();
        multimediaFile.setName(fileName);
        multimediaFile.setProperty(property.get());
        multimediaFileService.save(multimediaFile);

        UploadFileResponseDTO responseDTO = new UploadFileResponseDTO(
                fileName, file.getContentType(), file.getSize(), multimediaFile.getId());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convertToEntity();
        propertyService.updateProperty(id, property);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> deleteProperty(@PathVariable Long id) {
        propertyService.removeProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/multimedia/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> deletePropertyMultimediaFile(@PathVariable Long id) {
        MultimediaFile multimediaFile = multimediaFileService.findById(id);
        if (multimediaFile == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        fileStorageService.deleteFile(multimediaFile.getName(), multimediaFile.getId());
        multimediaFileService.delete(multimediaFile);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
