import React, { useEffect } from "react";
import { TileLayer, Popup, Marker, Map } from "react-leaflet";
import { Link } from "react-router-dom";
import L from "leaflet";

export const buildingIcon = new L.Icon({
  iconUrl: `${process.env.PUBLIC_URL}/building-solid.svg`,
  iconRetinaUrl: `${process.env.PUBLIC_URL}/building-solid.svg`,
  iconAnchor: [20, 40],
  popupAnchor: [0, -35],
  iconSize: [40, 40],
  shadowUrl: "../assets/marker-shadow.png",
  shadowSize: [29, 40],
  shadowAnchor: [7, 40],
});

export default function LeafletMap(props) {
  const mapRef = React.createRef();

  return (
    <>
      {true && (
        <Map
          center={[props.map.latitude, props.map.longitude]}
          zoom={props.map.zoom}
          preferCanvas={false}
          ref={mapRef}
        >
          <TileLayer
            attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          {props.properties &&
            props.properties.map((p) => {
              return (
                <Marker
                  position={[p.coordinate.latitude, p.coordinate.longitude]}
                  key={p.id}
                  icon={buildingIcon}
                >
                  <Popup>
                    <div className="card">
                      <div className="card-image">
                        <figure className="image is-4by3">
                          <img
                            src="https://bulma.io/images/placeholders/1280x960.png"
                            alt="Placeholder image"
                          />
                        </figure>
                      </div>
                      <div className="card-content">
                        <div className="media">
                          <div className="media-content">
                            <p className="title is-4">Price: {p.price}EUR</p>
                            <p className="subtitle is-6">
                              Size: {p.size}m<sup>2</sup>
                              <br />
                              Beds: {p.numberOfBeds}
                              <br />
                              Bathrooms: {p.numberOfBathrooms}
                              <br />
                              Find out more info{" "}
                              <Link to={"/" + p.id}>here.</Link>
                            </p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </Popup>
                </Marker>
              );
            })}
          {props.placesOfInterest &&
            props.placesOfInterest.map((p) => {
              return (
                <Marker
                  position={[p.coordinate.latitude, p.coordinate.longitude]}
                  key={p.id}
                >
                  <Popup>
                    <p>{p.typeOfPlace}</p>
                  </Popup>
                </Marker>
              );
            })}
        </Map>
      )}
    </>
  );
}
