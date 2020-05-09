import React, { useEffect } from "react";
import { TileLayer, Popup, Marker, Map } from "react-leaflet";
import { Link } from "react-router-dom";

export default function LeafletMap(props) {

    const mapRef = React.createRef();

    return (
        <>
        {props.properties && props.placesOfInterest && 
            <Map 
                center={[props.map.latitude, props.map.longitude]}
                zoom={props.map.zoom}
                preferCanvas={false}
                ref={mapRef}>
                <TileLayer
                attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {props.properties.map((p) => {
                    return (
                        <Marker
                            position={[
                                p.coordinate.latitude,
                                p.coordinate.longitude
                            ]}
                            key={p.id}
                            >
                            <Popup>
                            <div className="card">
                                <div className="card-image">
                                    <figure className="image is-4by3">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image"/>
                                    </figure>
                                </div>
                                <div className="card-content">
                                    <div className="media">
                                        <div className="media-content">
                                            <p className="title is-4">Price: {p.price}EUR</p>
                                            <p className="subtitle is-6">
                                                Size: {p.size}m<sup>2</sup>
                                                <br/>
                                                 Beds: {p.numberOfBeds}
                                                <br/>
                                                 Bathrooms: {p.numberOfBathrooms}
                                                <br/>
                                                Find out more info <Link to={"/" + p.id}>here.</Link>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </Popup>
                        </Marker>
                    )
                })}
                {props.placesOfInterest.map((p) => {
                    return (
                        <Marker
                        position={[
                            p.coordinate.latitude,
                            p.coordinate.longitude
                        ]}
                        key={p.id}
                        >
                            <Popup>
                                <p>{p.typeOfPlace}</p>
                            </Popup>
                        </Marker>
                    )
                })}
            </Map> 
        }
        </>
    )

}