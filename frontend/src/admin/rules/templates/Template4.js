import React from "react";
import { pets } from "../../../shared/Enums";

const Template4 = (props) => {
  return (
    <>
      <div className="field">
        <label className="label">Pet</label>
        <div className="control">
          <div className="select is-fullwidth">
            <select
              value={props.pet}
              onChange={props.inputChange("pet")}
            >
              {pets.map((pet) => (
                <option>{pet}</option>
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

export default Template4;
