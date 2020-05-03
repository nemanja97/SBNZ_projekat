import React, { useState } from "react";
import PropertyFilters from "./PropertyFilters";
import PersonalFilters from "./PersonalFilters";

export default function Filters(props) {
  const [activeTab, setActiveTab] = useState({
    tab: "property",
  });

  const handleTabChange = (value) => (event) => {
    event.preventDefault();
    setActiveTab({ ...activeTab, tab: value });
  };

  return (
    <>
      <div
        style={{ paddingLeft: "1em" }}
        className="tabs is-toggle is-fullwidth is-normal"
      >
        <ul>
          <li>
            <a onClick={handleTabChange("property")}>
              <span className="icon">
                <i className="fas fa-door-open"></i>
              </span>
              <span>Property information</span>
            </a>
          </li>
          <li>
            <a onClick={handleTabChange("personal")}>
              <span className="icon">
                <i className="far fa-user" aria-hidden="true"></i>
              </span>
              <span>Personal information</span>
            </a>
          </li>
        </ul>
      </div>
      {activeTab.tab === "property" && (
        <PropertyFilters
          propertyFilters={props.propertyFilters}
          handlePropertyFiltersChange={props.handlePropertyFiltersChange}
        />
      )}
      {activeTab.tab === "personal" && (
        <PersonalFilters
          personalFilters={props.personalFilters}
          handlePersonalFiltersChange={props.handlePersonalFiltersChange}
        />
      )}
      <div style={{ paddingLeft: "1em" }} className="field">
        <div className="control">
          <button
            className="button is-primary is-fullwidth"
            onClick={props.handleSearch}
          >
            Apply filters
          </button>
        </div>
      </div>
    </>
  );
}
