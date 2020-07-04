import React, { useState, useEffect } from "react";
import { PropertyService } from "../services/PropertyService";
import { useParams } from "react-router-dom";
import AdminNavbar from "../shared/AdminNavbar";
import { TileLayer, Popup, Marker, Map } from "react-leaflet";
import L from "leaflet";

function Property() {
  const [property, setProperty] = useState({
    price: undefined,
    size: undefined,
    coordinate: {
      latitude: undefined,
      longitude: undefined,
    },
    numberOfBeds: undefined,
    numberOfBathrooms: undefined,
    heating: [],
    allowedPets: [],
    amenities: [],
  });

  const [recommendationError, setRecommendationError] = useState(false);
  const [savedError, setSavedError] = useState(null);

  const params = useParams();

  const [map, setMap] = useState({
    latitude: 45.2517,
    longitude: 19.8369,
    zoom: 13,
  });

  const buildingIcon = new L.Icon({
    iconUrl: `${process.env.PUBLIC_URL}/building-solid.svg`,
    iconRetinaUrl: `${process.env.PUBLIC_URL}/building-solid.svg`,
    iconAnchor: [20, 40],
    popupAnchor: [0, -35],
    iconSize: [40, 40],
    shadowUrl: "../assets/marker-shadow.png",
    shadowSize: [29, 40],
    shadowAnchor: [7, 40],
  });

  const mapRef = React.createRef();

  // ****************************************************************************************************
  // Initial data
  // ****************************************************************************************************

  useEffect(() => {
    async function fetchProperty() {
      const response = await PropertyService.get(params.id);
      console.log(response.data);
      setProperty(response.data);
    }

    fetchProperty();
  }, []);

  // ****************************************************************************************************
  // Value change handling
  // ****************************************************************************************************

  const handleChange = (name) => (event) => {
    const value = event.target.value;
    setProperty({
      ...property,
      [name]: value,
    });
  };

  const handleHeatingClick = (name) => {
    setProperty({
      ...property,
      heating: name,
    });
  };

  const handlePetsClick = (name) => {
    if (property.allowedPets.includes(name)) {
      setProperty({
        ...property,
        allowedPets: property.allowedPets.filter((h) => h !== name),
      });
    } else {
      setProperty({
        ...property,
        allowedPets: [...property.allowedPets, name],
      });
    }
  };

  const handleAmenityClick = (name) => {
    if (property.amenities.includes(name)) {
      setProperty({
        ...property,
        amenities: property.amenities.filter((h) => h !== name),
      });
    } else {
      setProperty({
        ...property,
        amenities: [...property.amenities, name],
      });
    }
  };

  const checkIfHeatingSelected = (name) => {
    return property.heating.includes(name);
  };

  const checkIfPetSelected = (name) => {
    return property.allowedPets.includes(name);
  };

  const checkIfAmenitySelected = (name) => {
    return property.amenities.includes(name);
  };

  // ****************************************************************************************************
  // Value change handling
  // ****************************************************************************************************

  const handleGetRecommendation = async () => {
    try {
      const response = await PropertyService.priceRecommendation(property);
      setProperty(response.data);
      setRecommendationError(false);
    } catch (error) {
      setRecommendationError(true);
    }
  };

  const handleSaveProperty = async () => {
    try {
      if (params.id === undefined) {
        await PropertyService.addProperty(property);
      } else {
        await PropertyService.updateProperty(params.id, property);
      }
      setSavedError(false);
    } catch (error) {
      setSavedError(true);
    }
  };

  return (
    <>
      <AdminNavbar />
      <div className="container">
        {recommendationError && (
          <article className="message is-danger">
            <div className="message-header">
              <p>Warning</p>
              <button
                className="delete"
                aria-label="delete"
                onClick={() => setRecommendationError(false)}
              ></button>
            </div>
            <div className="message-body">
              Could not determine a recommended price.
            </div>
          </article>
        )}
        {savedError === true && (
          <article className="message is-danger">
            <div className="message-header">
              <p>Warning</p>
              <button
                className="delete"
                aria-label="delete"
                onClick={() => setSavedError(null)}
              ></button>
            </div>
            <div className="message-body">Could not save property.</div>
          </article>
        )}
        {savedError === false && (
          <article className="message is-success">
            <div className="message-header">
              <p>Success</p>
              <button
                className="delete"
                aria-label="delete"
                onClick={() => setSavedError(null)}
              ></button>
            </div>
            <div className="message-body">Property saved.</div>
          </article>
        )}
        <div className="columns">
          <div className="column">
            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Price</label>
              </div>
              <div className="field-body">
                <div className="field">
                  <p className="control is-expanded has-icons-left has-icons-right">
                    <input
                      className="input"
                      type="text"
                      placeholder="Price"
                      value={property.price}
                      onChange={handleChange("price")}
                    />
                    <span className="icon is-small is-left">
                      <i className="fas fa-euro-sign"></i>
                    </span>
                  </p>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Size</label>
              </div>
              <div className="field-body">
                <div className="field">
                  <p className="control is-expanded has-icons-left has-icons-right">
                    <input
                      className="input"
                      type="text"
                      placeholder="Size"
                      value={property.size}
                      onChange={handleChange("size")}
                    />
                    <span className="icon is-small is-left">
                      <i className="fas fa-building"></i>
                    </span>
                  </p>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Beds</label>
              </div>
              <div className="field-body">
                <div className="field">
                  <p className="control is-expanded has-icons-left has-icons-right">
                    <input
                      className="input"
                      type="text"
                      placeholder="Number of beds"
                      value={property.numberOfBeds}
                      onChange={handleChange("numberOfBeds")}
                    />
                    <span className="icon is-small is-left">
                      <i className="fas fa-bed"></i>
                    </span>
                  </p>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Bathrooms</label>
              </div>
              <div className="field-body">
                <div className="field">
                  <p className="control is-expanded has-icons-left has-icons-right">
                    <input
                      className="input"
                      type="text"
                      placeholder="Number of bathrooms"
                      value={property.numberOfBathrooms}
                      onChange={handleChange("numberOfBathrooms")}
                    />
                    <span className="icon is-small is-left">
                      <i className="fas fa-shower"></i>
                    </span>
                  </p>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Heating</label>
              </div>
              <div className="field-body">
                <div className="field is-grouped">
                  <div className="buttons">
                    <div className="control">
                      <button
                        className={
                          property && checkIfHeatingSelected("FURNACE")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleHeatingClick("FURNACE")}
                      >
                        Furnace
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfHeatingSelected("BOILER")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleHeatingClick("BOILER")}
                      >
                        Boiler
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfHeatingSelected("HEAT_PUMP")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleHeatingClick("HEAT_PUMP")}
                      >
                        Heat pump
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfHeatingSelected("HYBRID")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleHeatingClick("HYBRID")}
                      >
                        Hybrid
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property &&
                          checkIfHeatingSelected("DUCTLESS_MINI_SPLITS")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() =>
                          handleHeatingClick("DUCTLESS_MINI_SPLITS")
                        }
                      >
                        Ductless mini splits
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfHeatingSelected("RADIANT")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleHeatingClick("RADIANT")}
                      >
                        Radiant
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Pets</label>
              </div>
              <div className="field-body">
                <div className="field is-grouped">
                  <div className="buttons">
                    <div className="control">
                      <button
                        className={
                          property && checkIfPetSelected("CATS")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handlePetsClick("CATS")}
                      >
                        Cats
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfPetSelected("DOGS")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handlePetsClick("DOGS")}
                      >
                        Dogs
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfPetSelected("IN_AQUARIUM")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handlePetsClick("IN_AQUARIUM")}
                      >
                        In aquarium
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfPetSelected("IN_TERRARIUM")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handlePetsClick("IN_TERRARIUM")}
                      >
                        In terrarium
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <hr />

            <div className="field is-horizontal">
              <div className="field-label is-normal">
                <label className="label">Amenities</label>
              </div>
              <div className="field-body">
                <div className="field is-grouped">
                  <div className="buttons">
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("ELEVATOR")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("ELEVATOR")}
                      >
                        Elevator
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("AIR_CONDITIONING")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("AIR_CONDITIONING")}
                      >
                        Air conditioning
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("CABLE_READY")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("CABLE_READY")}
                      >
                        Cable ready
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property &&
                          checkIfAmenitySelected("HIGH_SPEED_INTERNET_ACCESS")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() =>
                          handleAmenityClick("HIGH_SPEED_INTERNET_ACCESS")
                        }
                      >
                        High speed internet
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("SWIMMING_POOL")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("SWIMMING_POOL")}
                      >
                        Swimming pool
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("GARAGE")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("GARAGE")}
                      >
                        Garage
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("SECURITY")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("SECURITY")}
                      >
                        On site security
                      </button>
                    </div>
                    <div className="control">
                      <button
                        className={
                          property && checkIfAmenitySelected("GATED")
                            ? "button is-primary"
                            : "button"
                        }
                        onClick={() => handleAmenityClick("GATED")}
                      >
                        Gated
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <hr />
            
            {property.status && (
              <div className="field is-horizontal">
                <div className="field-label is-normal">
                  <label className="label">Status</label>
                </div>
                <div className="field-body">
                  <div className="field">
                    <p className="control is-expanded has-icons-left has-icons-right">
                      <div class="control">
                        <div class="select is-fullwidth">
                          <select
                            value={property.status}
                            onChange={handleChange("status")}
                          >
                            <option>FOR_SALE</option>
                            <option>SOLD</option>
                            <option>TAKEN_DOWN</option>
                          </select>
                        </div>
                      </div>
                    </p>
                  </div>
                </div>
              </div>
            )}
          </div>
          <div className="column">
            <Map
              center={[map.latitude, map.longitude]}
              zoom={map.zoom}
              preferCanvas={false}
              ref={mapRef}
              onClick={(e) => {
                console.log(e.latlng);
                console.log(isNaN(e.latlng.lat));
                if (
                  e.latlng === null ||
                  e.latlng === undefined ||
                  e.latlng.lat === null ||
                  e.latlng.lat === undefined ||
                  e.latlng.lng === null ||
                  e.latlng.lng === undefined
                )
                  return;
                const coord = {
                  latitude: e.latlng.lat,
                  longitude: e.latlng.lng,
                };
                setProperty({ ...property, coordinate: coord });
              }}
            >
              <TileLayer
                attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              />
              {property.coordinate.latitude && property.coordinate.longitude && (
                <Marker
                  position={[
                    property.coordinate.latitude,
                    property.coordinate.longitude,
                  ]}
                  icon={buildingIcon}
                >
                  <Popup>
                    <div className="card">
                      <div className="card-image">
                        <figure className="image is-4by3">
                          <img
                            src="https://bulma.io/images/placeholders/1280x960.png"
                            alt="Placeholder image"
                          />
                        </figure>
                      </div>
                      <div className="card-content">
                        <div className="media">
                          <div className="media-content">
                            <p className="title is-4">
                              Price: {property.price}EUR
                            </p>
                            <p className="subtitle is-6">
                              Size: {property.size}m<sup>2</sup>
                              <br />
                              Beds: {property.numberOfBeds}
                              <br />
                              Bathrooms: {property.numberOfBathrooms}
                            </p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </Popup>
                </Marker>
              )}
            </Map>
          </div>
        </div>
        <button
          className="button is-fullwidth is-primary"
          onClick={() => handleGetRecommendation()}
          style={{ marginBottom: 10 }}
        >
          Get price recommendation
        </button>
        <button
          className="button is-fullwidth is-success"
          onClick={() => handleSaveProperty()}
        >
          Save property
        </button>
      </div>
    </>
  );
}

export default Property;
