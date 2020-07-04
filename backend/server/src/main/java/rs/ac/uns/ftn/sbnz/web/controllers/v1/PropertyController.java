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
import rs.ac.uns.ftn.sbnz.models.drools.PersonalInformation;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyInformation;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.models.drools.SmartSearch;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.Interest;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.service.FileStorageService;
import rs.ac.uns.ftn.sbnz.service.MultimediaFileService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.PropertyDTO;
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
    public ResponseEntity<List<PropertyDTO>> getOptimalProperties(
            @RequestParam(value = "priceLow", required = false, defaultValue = "0") int priceLow , @RequestParam(value = "priceHigh", required = false, defaultValue = "2147483647") int priceHigh,
            @RequestParam(value = "sizeLow", required = false, defaultValue = "0") int sizeLow, @RequestParam(value = "sizeHigh", required = false, defaultValue = "2147483647") int sizeHigh,
            @RequestParam(value = "bedsLow", required = false, defaultValue = "0") int bedsLow, @RequestParam(value = "bedsHigh", required = false, defaultValue = "2147483647") int bedsHigh,
            @RequestParam(value = "bathroomsLow", required = false, defaultValue = "0") int bathroomsLow, @RequestParam(value = "bathroomsHigh", required = false, defaultValue = "2147483647") int bathroomsHigh,
            @RequestParam(value = "heating", required = false, defaultValue = "FURNACE,BOILER,HEAT_PUMP,HYBRID,DUCTLESS_MINI_SPLITS,RADIANT") List<Heating> heating,
            @RequestParam(value = "pets", required = false, defaultValue = "") List<PetStatus> pets,
            @RequestParam(value = "amenities", required = false, defaultValue = "") List<Amenity> amenities,
            @RequestParam(value = "interests", required = false, defaultValue = "") List<Interest> interests,
            @RequestParam(value = "youngerOccupants", required = false, defaultValue = "0") int youngerOccupants,
            @RequestParam(value = "middleAgedOccupants", required = false, defaultValue = "0") int middleAgedOccupants,
            @RequestParam(value = "olderOccupants", required = false, defaultValue = "0") int olderOccupants,
            @RequestParam(value = "expectingChildren", required = false, defaultValue = "false") boolean expectingChildren,
            @RequestParam(value = "hasVehicle", required = false, defaultValue = "false") boolean hasVehicle
    ) {
        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(youngerOccupants, middleAgedOccupants, olderOccupants, expectingChildren, hasVehicle, interests),
                new PropertyInformation(priceLow, priceHigh, sizeLow, sizeHigh, bedsLow, bedsHigh, bathroomsLow, bathroomsHigh, heating, pets, amenities)
        );
    	ScoredProperties optimalProperties = propertyService.getOptimalProperties(smartSearch);
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

    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public ResponseEntity<PropertyDTO> recommend(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convertToEntity();
        property = propertyService.recommend(property);

        if (property == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new PropertyDTO(property), HttpStatus.OK);
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
