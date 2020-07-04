import React from "react";

function AdminPropertyBox(props) {
  return (
    <>
      <div className="box">
        <article className="media">
          <div className="media-left">
            <figure className="image is-64x64">
              <img src="https://bulma.io/images/placeholders/128x128.png" />
              <button
                style={{ width: "100%" }}
                className="button"
                onClick={() => props.edit(props.property.id)}
              >
                Edit
              </button>
            </figure>
          </div>
          <div className="media-content">
            <div className="content">
              <p>
                <strong>Price: {props.property.price}EUR</strong>{" "}
                {props.property.status == "SOLD" && (
                  <span className="tag is-info">Sold</span>
                )}
                {props.property.status == "TAKEN_DOWN" && (
                  <span className="tag is-danger">Taken down</span>
                )}
                {props.property.status == "FOR_SALE" && props.property.priceRecommendation == "LOWER" && (
                  <span className="tag is-warning">
                    Low interest in property, consider decreasing the price
                  </span>
                )}
                {props.property.status == "FOR_SALE" && props.property.priceRecommendation == "HIGHER" && (
                  <span className="tag is-success">
                    High interest in property, consider increasing the price
                  </span>
                )}
                <br />
                <strong>
                  {" "}
                  Size: {props.property.size}m<sup>2</sup>
                </strong>
                <strong> Beds: {props.property.numberOfBeds}</strong>
                <strong> Bathrooms: {props.property.numberOfBathrooms}</strong>
                <br />
                <div style={{ marginBottom: "14px" }} />
                <div className="field-body">
                  <div className="field is-grouped">
                    <div className="buttons has-addons">
                      <div className="control">
                        <button className="button is-primary">
                          {props.property.heating.replace(/_/g, " ")}
                        </button>
                      </div>
                      <div style={{ marginRight: "10px" }} />
                      {props.property.allowedPets.map((pet) => {
                        return (
                          <div className="control">
                            <button className="button is-primary">
                              {pet.replace(/_/g, " ")}
                            </button>
                          </div>
                        );
                      })}
                      <div style={{ marginRight: "10px" }} />
                      {props.property.amenities.map((amenity) => {
                        return (
                          <div className="control">
                            <button className="button is-primary">
                              {amenity.replace(/_/g, " ")}
                            </button>
                          </div>
                        );
                      })}
                    </div>
                  </div>
                </div>
              </p>
            </div>
          </div>
        </article>
      </div>
    </>
  );
}

export default AdminPropertyBox;
