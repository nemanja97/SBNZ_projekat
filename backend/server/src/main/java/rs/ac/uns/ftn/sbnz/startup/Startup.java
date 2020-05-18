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
//        property_1.setCoordinate(new Coordinate(45.239086, 19.838060));
//        property_1.setPrice(5000);
//        property_1.setSize(80);
//        property_1.setNumberOfBeds(2);
//        property_1.setNumberOfBathrooms(1);
//        property_1.setHeating(Heating.BOILER);
//        property_1.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
//        property_1.setAmenities(Set.of(Amenity.AIR_CONDITIONING, Amenity.CABLE_READY, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.ELEVATOR));
//        property_1.setStatus(PropertyStatus.FOR_SALE);
//
//        Property property_2 = new Property();
//        property_2.setCoordinate(new Coordinate(45.238056, 19.832311));
//        property_2.setPrice(12000);
//        property_2.setSize(100);
//        property_2.setNumberOfBeds(3);
//        property_2.setNumberOfBathrooms(1);
//        property_2.setHeating(Heating.RADIANT);
//        property_2.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS, PetStatus.IN_AQUARIUM, PetStatus.IN_TERRARIUM));
//        property_2.setAmenities(Set.of(Amenity.GATED, Amenity.GARAGE, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.ELEVATOR));
//        property_2.setStatus(PropertyStatus.FOR_SALE);
//
//        Property property_3 = new Property();
//        property_3.setCoordinate(new Coordinate(45.245325, 19.835154));
//        property_3.setPrice(9000);
//        property_3.setSize(90);
//        property_3.setNumberOfBeds(2);
//        property_3.setNumberOfBathrooms(1);
//        property_3.setHeating(Heating.DUCTLESS_MINI_SPLITS);
//        property_3.setAllowedPets(Set.of(PetStatus.IN_AQUARIUM, PetStatus.IN_TERRARIUM));
//        property_3.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.GARAGE, Amenity.CABLE_READY, Amenity.AIR_CONDITIONING));
//        property_3.setStatus(PropertyStatus.FOR_SALE);
//
//        Property property_4 = new Property();
//        property_4.setCoordinate(new Coordinate(45.252005, 19.831198));
//        property_4.setPrice(4500);
//        property_4.setSize(60);
//        property_4.setNumberOfBeds(1);
//        property_4.setNumberOfBathrooms(1);
//        property_4.setHeating(Heating.DUCTLESS_MINI_SPLITS);
//        property_4.setAllowedPets(Set.of(PetStatus.IN_AQUARIUM, PetStatus.IN_TERRARIUM));
//        property_4.setAmenities(Set.of(Amenity.GARAGE));
//        property_4.setStatus(PropertyStatus.FOR_SALE);
//
//        Property property_5 = new Property();
//        property_5.setCoordinate(new Coordinate(45.259640, 19.837315));
//        property_5.setPrice(10000);
//        property_5.setSize(100);
//        property_5.setNumberOfBeds(4);
//        property_5.setNumberOfBathrooms(2);
//        property_5.setHeating(Heating.RADIANT);
//        property_5.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
//        property_5.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.GARAGE, Amenity.CABLE_READY, Amenity.AIR_CONDITIONING, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.GATED));
//        property_5.setStatus(PropertyStatus.FOR_SALE);
//
//        List<Property> properties = Arrays.asList(
//                property_1,
//                property_2,
//                property_3,
//                property_4,
//                property_5
//        );
//
//        PlaceOfInterest placeOfInterest_1 = new PlaceOfInterest();
//        placeOfInterest_1.setCoordinate(new Coordinate(45.253746, 19.845421));
//        placeOfInterest_1.setTypeOfPlace(TypeOfPlace.CINEMA);
//
//        PlaceOfInterest placeOfInterest_2 = new PlaceOfInterest();
//        placeOfInterest_2.setCoordinate(new Coordinate(45.25036, 19.82142));
//        placeOfInterest_2.setTypeOfPlace(TypeOfPlace.HOSPITAL);
//
//        PlaceOfInterest placeOfInterest_3 = new PlaceOfInterest();
//        placeOfInterest_3.setCoordinate(new Coordinate(45.255239, 19.851209));
//        placeOfInterest_3.setTypeOfPlace(TypeOfPlace.PARK);
//
//        PlaceOfInterest placeOfInterest_4 = new PlaceOfInterest();
//        placeOfInterest_4.setCoordinate(new Coordinate(45.24906, 19.83813));
//        placeOfInterest_4.setTypeOfPlace(TypeOfPlace.BANK);
//
//        PlaceOfInterest placeOfInterest_5 = new PlaceOfInterest();
//        placeOfInterest_5.setCoordinate(new Coordinate(45.25528, 19.84319));
//        placeOfInterest_5.setTypeOfPlace(TypeOfPlace.THEATER);
//
//        PlaceOfInterest placeOfInterest_6 = new PlaceOfInterest();
//        placeOfInterest_6.setCoordinate(new Coordinate(45.24548, 19.84341));
//        placeOfInterest_6.setTypeOfPlace(TypeOfPlace.SHOPPING_CENTER);
//
//        PlaceOfInterest placeOfInterest_7 = new PlaceOfInterest();
//        placeOfInterest_7.setCoordinate(new Coordinate(45.24936, 19.84974));
//        placeOfInterest_7.setTypeOfPlace(TypeOfPlace.KINDERGARTEN);
//
//        PlaceOfInterest placeOfInterest_8 = new PlaceOfInterest();
//        placeOfInterest_8.setCoordinate(new Coordinate(45.2572, 19.8429));
//        placeOfInterest_8.setTypeOfPlace(TypeOfPlace.SCHOOL);
//
//        PlaceOfInterest placeOfInterest_9 = new PlaceOfInterest();
//        placeOfInterest_9.setCoordinate(new Coordinate(45.24652, 19.85112));
//        placeOfInterest_9.setTypeOfPlace(TypeOfPlace.UNIVERSITY);
//
//        PlaceOfInterest placeOfInterest_10 = new PlaceOfInterest();
//        placeOfInterest_10.setCoordinate(new Coordinate(45.24897, 19.83688));
//        placeOfInterest_10.setTypeOfPlace(TypeOfPlace.GYM);
//
//        PlaceOfInterest placeOfInterest_11 = new PlaceOfInterest();
//        placeOfInterest_11.setCoordinate(new Coordinate(45.24682, 19.84642));
//        placeOfInterest_11.setTypeOfPlace(TypeOfPlace.NIGHT_CLUB);
//
//        PlaceOfInterest placeOfInterest_12 = new PlaceOfInterest();
//        placeOfInterest_12.setCoordinate(new Coordinate(45.25428, 19.82096));
//        placeOfInterest_12.setTypeOfPlace(TypeOfPlace.RESTAURANT);
//
//        PlaceOfInterest placeOfInterest_13 = new PlaceOfInterest();
//        placeOfInterest_13.setCoordinate(new Coordinate(45.24575, 19.84503));
//        placeOfInterest_13.setTypeOfPlace(TypeOfPlace.SUPERMARKET);
//
//        List<PlaceOfInterest> placesOfInterest = Arrays.asList(
//                placeOfInterest_1,
//                placeOfInterest_2,
//                placeOfInterest_3,
//                placeOfInterest_4,
//                placeOfInterest_5,
//                placeOfInterest_6,
//                placeOfInterest_7,
//                placeOfInterest_8,
//                placeOfInterest_9,
//                placeOfInterest_10,
//                placeOfInterest_11,
//                placeOfInterest_12,
//                placeOfInterest_13
//        );
//
//        properties.forEach(property -> propertyService.addProperty(property));
//        placesOfInterest.forEach(placeOfInterest -> placeOfInterestService.addPlaceOfInterest(placeOfInterest));
    }
}
