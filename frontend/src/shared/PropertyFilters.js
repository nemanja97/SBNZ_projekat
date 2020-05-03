import React from "react";

export default function PropertyFilters(props) {
  
  const handleChange = (name) => (event) => {
    const value = event.target.value;
    props.handlePropertyFiltersChange({
      ...props.propertyFilters,
      [name]: value,
    });
  };

  const handleHeatingClick = (name) => {
    if (props.propertyFilters.heating.includes(name)) {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        heating: props.propertyFilters.heating.filter((h) => h !== name),
      });
    } else {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        heating: [...props.propertyFilters.heating, name],
      });
    }
  };

  const handlePetsClick = (name) => {
    if (props.propertyFilters.pets.includes(name)) {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        pets: props.propertyFilters.pets.filter((h) => h !== name),
      });
    } else {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        pets: [...props.propertyFilters.pets, name],
      });
    }
  };

  const handleAmenityClick = (name) => {
    if (props.propertyFilters.amenities.includes(name)) {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        amenities: props.propertyFilters.amenities.filter((h) => h !== name),
      });
    } else {
      props.handlePropertyFiltersChange({
        ...props.propertyFilters,
        amenities: [...props.propertyFilters.amenities, name],
      });
    }
  };

  const checkIfHeatingSelected = (name) => {
    return props.propertyFilters.heating.includes(name);
  };

  const checkIfPetSelected = (name) => {
    return props.propertyFilters.pets.includes(name);
  };

  const checkIfAmenitySelected = (name) => {
    return props.propertyFilters.amenities.includes(name);
  };

  return (
    <>
      <div className="field is-horizontal">
        <div className="field-label is-normal">
          <label className="label">Price range</label>
        </div>
        <div className="field-body">
          <div className="field">
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="From"
                value={props.propertyFilters.priceLow}
                onChange={handleChange("priceLow")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-euro-sign"></i>
              </span>
            </p>
          </div>
          <div className="field">
            <p className="control is-expanded has-icons-left has-icons-right">
              <input
                className="input"
                type="text"
                placeholder="To"
                value={props.propertyFilters.priceHigh}
                onChange={handleChange("priceHigh")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-euro-sign"></i>
              </span>
            </p>
          </div>
        </div>
      </div>

      <div className="field is-horizontal">
        <div className="field-label is-normal">
          <label className="label">
            Size (m<sup>2</sup>)
          </label>
        </div>
        <div className="field-body">
          <div className="field">
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="From"
                value={props.propertyFilters.sizeLow}
                onChange={handleChange("sizeLow")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-building"></i>
              </span>
            </p>
          </div>
          <div className="field">
            <p className="control is-expanded has-icons-left has-icons-right">
              <input
                className="input"
                type="text"
                placeholder="To"
                value={props.propertyFilters.sizeHigh}
                onChange={handleChange("sizeHigh")}
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
          <label className="label">Number of beds</label>
        </div>
        <div className="field-body">
          <div className="field">
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="From"
                value={props.propertyFilters.bedsLow}
                onChange={handleChange("bedsLow")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-bed"></i>
              </span>
            </p>
          </div>
          <div className="field">
            <p className="control is-expanded has-icons-left has-icons-right">
              <input
                className="input"
                type="text"
                placeholder="To"
                value={props.propertyFilters.bedsHigh}
                onChange={handleChange("bedsHigh")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-bed"></i>
              </span>
            </p>
          </div>
        </div>
      </div>
      <div className="field is-horizontal">
        <div className="field-label is-normal">
          <label className="label">Number of bathrooms</label>
        </div>
        <div className="field-body">
          <div className="field">
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="From"
                value={props.propertyFilters.bathroomsLow}
                onChange={handleChange("bathroomsLow")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-shower"></i>
              </span>
            </p>
          </div>
          <div className="field">
            <p className="control is-expanded has-icons-left has-icons-right">
              <input
                className="input"
                type="text"
                placeholder="To"
                value={props.propertyFilters.bathroomsHigh}
                onChange={handleChange("bathroomsHigh")}
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
                    props.propertyFilters && checkIfHeatingSelected("FURNACE")
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
                    props.propertyFilters && checkIfHeatingSelected("BOILER")
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
                    props.propertyFilters && checkIfHeatingSelected("HEAT_PUMP")
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
                    props.propertyFilters && checkIfHeatingSelected("HYBRID")
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
                    props.propertyFilters &&
                    checkIfHeatingSelected("DUCTLESS_MINI_SPLITS")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleHeatingClick("DUCTLESS_MINI_SPLITS")}
                >
                  Ductless mini splits
                </button>
              </div>
              <div className="control">
                <button
                  className={
                    props.propertyFilters && checkIfHeatingSelected("RADIANT")
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
                    props.propertyFilters && checkIfPetSelected("CATS")
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
                    props.propertyFilters && checkIfPetSelected("DOGS")
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
                    props.propertyFilters && checkIfPetSelected("IN_AQUARIUM")
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
                    props.propertyFilters && checkIfPetSelected("IN_TERRARIUM")
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
                    props.propertyFilters && checkIfAmenitySelected("ELEVATOR")
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
                    props.propertyFilters &&
                    checkIfAmenitySelected("AIR_CONDITIONING")
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
                    props.propertyFilters &&
                    checkIfAmenitySelected("CABLE_READY")
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
                    props.propertyFilters &&
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
                    props.propertyFilters &&
                    checkIfAmenitySelected("SWIMMING_POOL")
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
                    props.propertyFilters && checkIfAmenitySelected("GARAGE")
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
                    props.propertyFilters && checkIfAmenitySelected("SECURITY")
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
                    props.propertyFilters && checkIfAmenitySelected("GATED")
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
    </>
  );
}
