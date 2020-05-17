import React, { useState, useEffect } from "react";
import Navbar from "../shared/Navbar";
import Filters from "../shared/Filters";
import { PropertyService } from "../services/PropertyService";
import { PlacesOfInterestService } from "../services/PlacesOfInterestService";
import LeafletMap from "../shared/LeafletMap";
import qs from "querystring"

function HomePage() {
  const [map, setMap] = useState({
    latitude: 45.2517,
    longitude: 19.8369,
    zoom: 13,
  });

  const [properties, setProperties] = useState()

  const [placesOfInterest, setPlacesOfInterest] = useState()

  const [propertyFilters, setPropertyFilters] = useState({
    priceLow: '',
    priceHigh: '',
    sizeLow: '',
    sizeHigh: '',
    bedsLow: '',
    bedsHigh: '',
    bathroomsLow: '',
    bathroomsHigh: '',
    heating: [],
    pets: [],
    amenities: [],
  });

  const [personalFilters, setPersonalFilters] = useState({
    youngerOccupants: '',
    middleAgedOccupants: '',
    olderOccupants: '',
    expectingChildren: undefined,
    hasVehicle: undefined,
    interests: [],
  });

  const handleSearch = (event) => {
    event.preventDefault();
    const smartSearch = {
      ...propertyFilters,
      ...personalFilters,
    }
    window.open("/results?" + qs.stringify(smartSearch), '_blank')
  };

  useEffect(() => {
    async function fetchProperties() {
      const response = await PropertyService.getAll();
      setProperties(response.data);
    }
    async function fetchPlacesOfInterest() {
      const response = await PlacesOfInterestService.getAll();
      setPlacesOfInterest(response.data);
    }

    fetchProperties();
    fetchPlacesOfInterest();
  }, [])

  return (
    <>
      <Navbar />
      <div className="columns">
        <div className="column is-one-third">
          <section className="hero">
            <div className="hero-body">
              <div className="container">
                <h1 className="title">Welcome to Property.io</h1>
                <h2 className="subtitle">
                  Use the filters bellow to find properties for sale that best
                  fit your needs.
                </h2>
              </div>
            </div>
          </section>
          <Filters
            propertyFilters={propertyFilters}
            handlePropertyFiltersChange={setPropertyFilters}

            personalFilters={personalFilters}
            handlePersonalFiltersChange={setPersonalFilters}

            handleSearch={handleSearch}
          />
        </div>
        <div
          className="column"
          style={{
            minHeight: "95vh",
          }}
        >
          <LeafletMap
            map={map}
            handleMapChange={setMap}
            
            properties={properties}
            handlePropertiesChange={setProperties}
            
            placesOfInterest={placesOfInterest}
            handleplacesOfInterestChange={setPlacesOfInterest}/>
        </div>
      </div>
    </>
  );
}

export default HomePage;
