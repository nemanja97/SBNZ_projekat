import React from "react";

export default function PersonalFilters(props) {
  const handleChange = (name) => (event) => {
    const value = event.target.value;
    props.handlePersonalFiltersChange({
      ...props.personalFilters,
      [name]: value,
    });
  };

  const setExpectingChildren = (val) => {
    props.handlePersonalFiltersChange({
      ...props.personalFilters,
      expectingChildren: val,
    });
  };

  const setHasVehicle = (val) => {
    props.handlePersonalFiltersChange({
      ...props.personalFilters,
      hasVehicle: val,
    });
  };

  const handleInterestClick = (name) => {
    if (props.personalFilters.interests.includes(name)) {
      props.handlePersonalFiltersChange({
        ...props.personalFilters,
        interests: props.personalFilters.interests.filter((i) => i !== name),
      });
    } else {
      props.handlePersonalFiltersChange({
        ...props.personalFilters,
        interests: [...props.personalFilters.interests, name],
      });
    }
  };

  const checkIfInterestSelected = (name) => {
    return props.personalFilters.interests.includes(name);
  };

  return (
    <>
      <div className="field is-horizontal">
        <div className="field-label is-normal">
          <label className="label">Occupant ages:</label>
        </div>
        <div className="field-body">
          <div className="field is-grouped-multiline">
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="Number of younger occupants (<30)"
                value={props.personalFilters.youngerOccupants}
                onChange={handleChange("youngerOccupants")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-users"></i>
              </span>
            </p>
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="Number of middle aged occupants (30-60)"
                value={props.personalFilters.middleAgedOccupants}
                onChange={handleChange("middleAgedOccupants")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-users"></i>
              </span>
            </p>
            <p className="control is-expanded has-icons-left">
              <input
                className="input"
                type="text"
                placeholder="Number of older occupants (60+)"
                value={props.personalFilters.olderOccupants}
                onChange={handleChange("olderOccupants")}
              />
              <span className="icon is-small is-left">
                <i className="fas fa-users"></i>
              </span>
            </p>
          </div>
        </div>
      </div>
      <hr />
      <div style={{ paddingLeft: "2em" }} className="columns">
        <div className="column">
          <div className="field is-horizontal">
            <div className="field-label">
              <label className="label">Expecting children?</label>
            </div>
            <div className="field-body">
              <div className="field is-narrow">
                <div className="control">
                  <label className="radio">
                    <input
                      type="radio"
                      name="expecting_children"
                      checked={props.personalFilters.expectingChildren === true}
                      value={true}
                      onChange={() => setExpectingChildren(true)}
                    />
                    Yes
                  </label>
                  <label className="radio">
                    <input
                      type="radio"
                      name="expecting_children"
                      checked={
                        props.personalFilters.expectingChildren === false
                      }
                      value={false}
                      onChange={() => setExpectingChildren(false)}
                    />
                    No
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="column">
          <div className="field is-horizontal">
            <div className="field-label">
              <label className="label">Do you have a vehicle?</label>
            </div>
            <div className="field-body">
              <div className="field is-narrow">
                <div className="control">
                  <label className="radio">
                    <input
                      type="radio"
                      name="have_vehicle"
                      checked={props.personalFilters.hasVehicle === true}
                      value={true}
                      onChange={() => setHasVehicle(true)}
                    />
                    Yes
                  </label>
                  <label className="radio">
                    <input
                      type="radio"
                      name="have_vehicle"
                      checked={props.personalFilters.hasVehicle === false}
                      value={false}
                      onChange={() => setHasVehicle(false)}
                    />
                    No
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr />
      <div className="field is-horizontal">
        <div className="field-label is-normal">
          <label className="label">Interests</label>
        </div>
        <div className="field-body">
          <div className="field is-grouped">
            <div className="buttons">
              <div className="control">
                <button
                  className={
                    props.personalFilters && checkIfInterestSelected("FOOD")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleInterestClick("FOOD")}
                >
                  Fine dining
                </button>
              </div>
              <div className="control">
                <button
                  className={
                    props.personalFilters && checkIfInterestSelected("NATURE")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleInterestClick("NATURE")}
                >
                  Walks in nature
                </button>
              </div>
              <div className="control">
                <button
                  className={
                    props.personalFilters && checkIfInterestSelected("SPORT")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleInterestClick("SPORT")}
                >
                  Sport events
                </button>
              </div>
              <div className="control">
                <button
                  className={
                    props.personalFilters && checkIfInterestSelected("CULTURE")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleInterestClick("CULTURE")}
                >
                  Cultural manifestations
                </button>
              </div>
              <div className="control">
                <button
                  className={
                    props.personalFilters && checkIfInterestSelected("NIGHT_LIFE")
                      ? "button is-primary"
                      : "button"
                  }
                  onClick={() => handleInterestClick("NIGHT_LIFE")}
                >
                  Night life
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
