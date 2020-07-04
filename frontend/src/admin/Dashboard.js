import React, { useState, useEffect } from "react";
import { PropertyService } from "../services/PropertyService";
import { useHistory } from "react-router-dom";
import AdminNavbar from "../shared/AdminNavbar";
import AdminPropertyList from "../shared/AdminPropertyList";

function Dashboard() {
  const [properties, setProperties] = useState();
  const history = useHistory();

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
      <div className="container">
        <AdminPropertyList
          properties={properties}
          edit={(id) => {
            history.push(`/admin/property/${id}`);
          }}
        />
        <button
          style={{ marginBottom: 10 }}
          className="button is-fullwidth is-primary"
          onClick={() => history.push(`/admin/property`)}
        >
          Add new property
        </button>
      </div>
    </>
  );
}

export default Dashboard;
