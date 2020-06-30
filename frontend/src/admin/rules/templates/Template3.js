import React from "react";
import { heating } from "../../../shared/Enums";

const Template3 = (props) => {
  return (
    <>
      <div className="field">
        <label className="label">Heating</label>
        <div className="control">
          <div className="select is-fullwidth">
            <select
              value={props.heating}
              onChange={props.inputChange("heating")}
            >
              {heating.map((heat) => (
                <option>{heat}</option>
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

export default Template3;
