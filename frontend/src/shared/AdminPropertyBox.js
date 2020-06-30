import React from "react";

function AdminPropertyBox(props) {
  return (
    <>
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image is-64x64">
              <img src="https://bulma.io/images/placeholders/128x128.png" />
              <button style={{ width: "100%" }} className="button">
                Edit
              </button>
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>Price: {props.property.price}EUR</strong>{" "}
                {props.property.priceRecommendation == "LOWER" && (
                  <span class="tag is-warning">
                    Low interest in property, consider decreasing the price
                  </span>
                )}
                {props.property.priceRecommendation == "HIGHER" && (
                  <span class="tag is-success">
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
