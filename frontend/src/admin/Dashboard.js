import React, { useState, useEffect } from "react";
import { PropertyService } from "../services/PropertyService";
import PropertyFilters from "../shared/PropertyFilters";
import AdminNavbar from "../shared/AdminNavbar";
import AdminPropertyList from "../shared/AdminPropertyList";

function Dashboard() {
  const [properties, setProperties] = useState();

  const [propertyFilters, setPropertyFilters] = useState({
    priceLow: "",
    priceHigh: "",
    sizeLow: "",
    sizeHigh: "",
    bedsLow: "",
    bedsHigh: "",
    bathroomsLow: "",
    bathroomsHigh: "",
    heating: [],
    pets: [],
    amenities: [],
  });

  useEffect(() => {
    async function fetchProperties() {
      const response = await PropertyService.getAll();
      console.log(response.data);
      setProperties(response.data);
    }

    fetchProperties();
  }, []);

  return (
    <>
      <AdminNavbar />
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
          <PropertyFilters
            propertyFilters={propertyFilters}
            handlePropertyFiltersChange={setPropertyFilters}
          />
        </div>
        <div
          className="column"
          style={{
            minHeight: "95vh",
          }}
        >
          <AdminPropertyList properties={properties}/>
        </div>
      </div>
    </>
  );
}

export default Dashboard;
