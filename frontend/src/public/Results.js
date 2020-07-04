import React, { useState, useEffect } from "react";
import Navbar from "../shared/Navbar";
import { useLocation } from "react-router-dom";
import { PropertyService } from "../services/PropertyService";
import { AnalyticsService } from "../services/AnalyticsService";
import { PlacesOfInterestService } from "../services/PlacesOfInterestService";
import LeafletMap from "../shared/LeafletMap";
import ResultsContainer from "../shared/ResultsContainer";

function ResultsPage(props) {
  const [map, setMap] = useState({
    latitude: 45.2517,
    longitude: 19.8369,
    zoom: 13,
  });

  const [properties, setProperties] = useState();

  const [placesOfInterest, setPlacesOfInterest] = useState();

  const [page, setPage] = useState({ value: 0 });

  const location = useLocation();

  useEffect(() => {
    async function fetchProperties() {
      const response = await PropertyService.findOptimal(
        new URLSearchParams(location.search)
      );
      console.log(response.data);
      setProperties(response.data);
    }
    async function fetchPlacesOfInterest() {
      const response = await PlacesOfInterestService.getAll();
      setPlacesOfInterest(response.data);
    }
    fetchProperties();
    fetchPlacesOfInterest();
  }, [location]);

  const handleMoreInfoClick = async (id) => {
    await AnalyticsService.moreInfoClick(id);
  };

  return (
    <>
      <div style={{ paddingLeft: "1em" }}>
        <Navbar />
        <div className="columns">
          <div className="column is-one-third">
            <ResultsContainer
              properties={properties}
              page={page}
              handlePageChange={setPage}
              handleMoreInfoClick={handleMoreInfoClick}
            />
          </div>
          <div
            className="column"
            style={{
              minHeight: "95vh",
            }}
          >
            <LeafletMap
              map={map}
              handleMapChange={setMap}
              properties={properties}
              handlePropertiesChange={setProperties}
              placesOfInterest={placesOfInterest}
              handleplacesOfInterestChange={setPlacesOfInterest}
              handleMoreInfoClick={handleMoreInfoClick}
            />
          </div>
        </div>
      </div>
    </>
  );
}

export default ResultsPage;
