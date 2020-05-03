import React, { useState } from "react";
import { TileLayer, Popup, Marker, Map } from "react-leaflet";
import Navbar from "../shared/Navbar";
import Filters from "../shared/Filters";

function HomePage() {
  const [map, setMap] = useState({
    latitude: 45.2517,
    longitude: 19.8369,
    zoom: 13,
  });

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
    console.log(propertyFilters);
    console.log(personalFilters);
  };

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
          <Map center={[map.latitude, map.longitude]} zoom={map.zoom}>
            <TileLayer
              attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={[map.latitude, map.longitude]}>
              <Popup>
                A pretty CSS3 popup. <br /> Easily customizable.
              </Popup>
            </Marker>
          </Map>
        </div>
      </div>
    </>
  );
}

export default HomePage;
