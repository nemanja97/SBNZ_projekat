import React from "react";
import { placeTypes } from "../../../shared/Enums";

const Template2 = (props) => {
  return (
    <>
      <div className="field">
        <label className="label">Type of place</label>
        <div className="control">
          <div className="select is-fullwidth">
            <select
              value={props.place}
              onChange={props.inputChange("place")}
            >
              {placeTypes.map((place) => (
                <option>{place}</option>
              ))}
            </select>
          </div>
        </div>
      </div>
      <input
        className="input"
        placeholder="Value"
        value={props.value}
        onChange={props.inputChange("value")}
      />
    </>
  );
};

export default Template2;
