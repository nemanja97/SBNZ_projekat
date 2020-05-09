package rs.ac.uns.ftn.sbnz.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.sbnz.models.*;
import rs.ac.uns.ftn.sbnz.models.enums.*;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class Startup implements ApplicationRunner {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PlaceOfInterestService placeOfInterestService;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        User user = new User();
//        user.setEmail("a@a.com");
//        user.setPassword("admin");
//        user.setRole(Role.ROLE_ADMIN);
//        userService.registerAUser(user);
//
//        Property property_1 = new Property();
//        property_1.setCoordinate(new Coordinate(45.02, 25.6));
//        property_1.setPrice(5000);
//        property_1.setSize(80);
//        property_1.setNumberOfBeds(2);
//        property_1.setNumberOfBathrooms(1);
//        property_1.setHeating(Heating.BOILER);
//        property_1.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS, PetStatus.IN_AQUARIUM, PetStatus.IN_TERRARIUM));
//        property_1.setAmenities(Set.of(Amenity.AIR_CONDITIONING, Amenity.CABLE_READY, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.ELEVATOR));
//        property_1.setStatus(PropertyStatus.FOR_SALE);
//
//        Property property_2 = new Property();
//        property_2.setCoordinate(new Coordinate(46.02, 35.6));
//        property_2.setPrice(50000);
//        property_2.setSize(200);
//        property_2.setNumberOfBeds(4);
//        property_2.setNumberOfBathrooms(2);
//        property_2.setHeating(Heating.RADIANT);
//        property_2.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
//        property_2.setAmenities(Set.of(Amenity.GATED, Amenity.GARAGE, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.ELEVATOR));
//        property_2.setStatus(PropertyStatus.FOR_SALE);
//
//        List<Property> properties = Arrays.asList(
//                property_1,
//                property_2
//        );
//
//        PlaceOfInterest placeOfInterest_1 = new PlaceOfInterest();
//        placeOfInterest_1.setCoordinate(new Coordinate(47.02, 30.6));
//        placeOfInterest_1.setTypeOfPlace(TypeOfPlace.BANK);
//
//        PlaceOfInterest placeOfInterest_2 = new PlaceOfInterest();
//        placeOfInterest_2.setCoordinate(new Coordinate(46.02, 30.6));
//        placeOfInterest_2.setTypeOfPlace(TypeOfPlace.HOSPITAL);
//
//        PlaceOfInterest placeOfInterest_3 = new PlaceOfInterest();
//        placeOfInterest_3.setCoordinate(new Coordinate(45.02, 30.6));
//        placeOfInterest_3.setTypeOfPlace(TypeOfPlace.PARK);
//
//        List<PlaceOfInterest> placesOfInterest = Arrays.asList(
//                placeOfInterest_1,
//                placeOfInterest_2,
//                placeOfInterest_3
//        );
//
//        properties.forEach(property -> propertyService.addProperty(property));
//        placesOfInterest.forEach(placeOfInterest -> placeOfInterestService.addPlaceOfInterest(placeOfInterest));
    }
}
