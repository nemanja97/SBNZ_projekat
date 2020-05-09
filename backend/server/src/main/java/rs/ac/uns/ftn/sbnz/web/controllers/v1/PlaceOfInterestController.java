package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.PlaceOfInterestDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/placesOfInterest")
@CrossOrigin
public class PlaceOfInterestController {

    @Autowired
    private PlaceOfInterestService placeOfInterestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PlaceOfInterestDTO>> getPlacesOfInterest() {
        List<PlaceOfInterest> properties = placeOfInterestService.getPlacesOfInterest();
        List<PlaceOfInterestDTO> propertyDTOS = properties.stream().map(PlaceOfInterestDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlaceOfInterestDTO> getPlaceOfInterest(@PathVariable Long id) {
        Optional<PlaceOfInterest> property = placeOfInterestService.getPlaceOfInterest(id);

        return property.map(value -> new ResponseEntity<>(new PlaceOfInterestDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> addPlaceOfInterest(@Valid @RequestBody PlaceOfInterestDTO placeOfInterestDTO) {
        PlaceOfInterest placeOfInterest =  placeOfInterestDTO.convertToEntity();
        placeOfInterestService.addPlaceOfInterest(placeOfInterest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Object> updatePlaceOfInterest(@PathVariable Long id,
                                                        @Valid @RequestBody PlaceOfInterestDTO placeOfInterestDTO) {
        PlaceOfInterest placeOfInterest = placeOfInterestDTO.convertToEntity();
        placeOfInterestService.updatePlaceOfInterest(id, placeOfInterest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceOfInterest(@PathVariable Long id) {
        placeOfInterestService.removePlaceOfInterest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
