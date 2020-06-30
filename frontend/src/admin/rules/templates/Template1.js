import React from "react";
import { amenities } from "../../../shared/Enums";

const Template1 = (props) => {
  return (
    <>
      <div className="field">
        <label className="label">Amenity</label>
        <div className="control">
          <div className="select is-fullwidth">
            <select
              value={props.amenity}
              onChange={props.inputChange("amenity")}
            >
              {amenities.map((amenity) => (
                <option>{amenity}</option>
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

export default Template1;
