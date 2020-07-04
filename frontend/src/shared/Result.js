import React from "react";

export default function Result(props) {
  return (
    <>
      <div class="box">
        <div class="card-image" style={{ marginBottom: 20 }}>
          <figure class="image">
            <img
              src="https://via.placeholder.com/468x248"
              alt="Placeholder image"
            />
          </figure>
        </div>

        <div className="field is-horizontal">
          <div className="field-label is-normal">
            <label className="label">Price</label>
          </div>
          <div className="field-body">
            <div className="field">
              <p className="control is-expanded has-icons-left">
                <input
                  className="input"
                  type="text"
                  value={props.property.price}
                  readOnly
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
            <label className="label">Size</label>
          </div>
          <div className="field-body">
            <div className="field">
              <p className="control is-expanded has-icons-left">
                <input
                  className="input"
                  type="text"
                  value={props.property.size}
                  readonly
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
            <label className="label">Beds</label>
          </div>
          <div className="field-body">
            <div className="field">
              <p className="control is-expanded has-icons-left">
                <input
                  className="input"
                  type="text"
                  value={props.property.numberOfBeds}
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
            <label className="label">Bathrooms</label>
          </div>
          <div className="field-body">
            <div className="field">
              <p className="control is-expanded has-icons-left">
                <input
                  className="input"
                  type="text"
                  value={props.property.numberOfBathrooms}
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
                  <button className={"button is-primary"}>
                    {props.property.heating === "FURNACE"
                      ? "Furnace"
                      : undefined}
                    {props.property.heating === "BOILER" ? "Boiler" : undefined}
                    {props.property.heating === "HEAT_PUMP"
                      ? "Heat pump"
                      : undefined}
                    {props.property.heating === "HYBRID" ? "Hybrid" : undefined}
                    {props.property.heating === "DUCTLESS_MINI_SPLITS"
                      ? "Ductless mini splits"
                      : undefined}
                    {props.property.heating === "RADIANT"
                      ? "Radiant"
                      : undefined}
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
                {props.property.allowedPets &&
                  props.property.allowedPets.map((p, idx) => {
                    return (
                      <div className="control">
                        <button key={idx} className={"button is-primary"}>
                          {p === "CATS" ? "Cats" : undefined}
                          {p === "DOGS" ? "Dogs" : undefined}
                          {p === "IN_AQUARIUM" ? "In aquarium" : undefined}
                          {p === "IN_TERRARIUM" ? "In terrarium" : undefined}
                        </button>
                      </div>
                    );
                  })}
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
                {props.property.amenities &&
                  props.property.amenities.map((a, idx) => {
                    return (
                      <div className="control">
                        <button key={idx} className={"button is-primary"}>
                          {a === "ELEVATOR" ? "Elevator" : undefined}
                          {a === "AIR_CONDITIONING"
                            ? "Air conditioning"
                            : undefined}
                          {a === "CABLE_READY" ? "Cable ready" : undefined}
                          {a === "HIGH_SPEED_INTERNET_ACCESS"
                            ? "High speed internet"
                            : undefined}
                          {a === "SWIMMING_POOL" ? "Swimming pool" : undefined}
                          {a === "GARAGE" ? "Garage" : undefined}
                          {a === "SECURITY" ? "On site security" : undefined}
                          {a === "GATED" ? "Gated" : undefined}
                        </button>
                      </div>
                    );
                  })}
              </div>
            </div>
          </div>
        </div>

        <button
          style={{ marginTop: 10 }}
          onClick={() => props.handleMoreInfoClick(props.property.id)}
          className="button is-fullwidth"
        >
          More info
        </button>
      </div>
    </>
  );
}
