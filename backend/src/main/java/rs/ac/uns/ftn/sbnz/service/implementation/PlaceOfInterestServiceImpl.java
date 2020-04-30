package rs.ac.uns.ftn.sbnz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.PlaceOfInterestNotFoundException;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.repository.PlaceOfInterestRepository;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceOfInterestServiceImpl implements PlaceOfInterestService {

    @Autowired
    private PlaceOfInterestRepository placeOfInterestRepository;

    @Override
    public void addPlaceOfInterest(PlaceOfInterest placeOfInterest) {
        placeOfInterestRepository.save(placeOfInterest);
    }

    @Override
    public List<PlaceOfInterest> getPlacesOfInterest() {
        return placeOfInterestRepository.findAll();
    }

    @Override
    public Optional<PlaceOfInterest> getPlaceOfInterest(Long id) {
        return placeOfInterestRepository.findById(id);
    }

    @Override
    public void updatePlaceOfInterest(Long id, PlaceOfInterest placeOfInterest) {
        Optional<PlaceOfInterest> optionalPlaceOfInterest = this.getPlaceOfInterest(id);
        if (optionalPlaceOfInterest.isPresent()) {
            PlaceOfInterest oldPlaceOfInterest = new PlaceOfInterest();
            oldPlaceOfInterest.setCoordinate(placeOfInterest.getCoordinate());
            oldPlaceOfInterest.setTypeOfPlace(placeOfInterest.getTypeOfPlace());
            placeOfInterestRepository.save(oldPlaceOfInterest);
        } else {
            throw new PlaceOfInterestNotFoundException();
        }
    }

    @Override
    public void removePlaceOfInterest(Long id) {
        Optional<PlaceOfInterest> optionalPlaceOfInterest = this.getPlaceOfInterest(id);
        if (optionalPlaceOfInterest.isPresent()) {
            placeOfInterestRepository.delete(optionalPlaceOfInterest.get());
        } else {
            throw new PlaceOfInterestNotFoundException();
        }
    }
}
