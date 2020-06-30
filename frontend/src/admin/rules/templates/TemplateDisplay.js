import React from "react";
import Template1 from "./Template1";
import Template2 from "./Template2";
import Template4 from "./Template4";
import Template3 from "./Template3";

const TemplateDisplay = (props) => {
  return (
    <>
      <div className="container is-fluid">
        <div className="columns is-mobile">
          <div className="column is-9">
            {props.activeTemplate === 1 && (
              <Template1
                amenity={props.variables[1].amenity}
                value={props.variables[1].value}
                inputChange={props.inputChange}
              />
            )}
            {props.activeTemplate === 2 && (
              <Template2
                place={props.variables[2].place}
                value={props.variables[2].value}
                inputChange={props.inputChange}
              />
            )}
            {props.activeTemplate === 3 && (
              <Template3
                heating={props.variables[3].heating}
                value={props.variables[3].value}
                inputChange={props.inputChange}
              />
            )}
            {props.activeTemplate === 4 && (
              <Template4
                pet={props.variables[4].pet}
                value={props.variables[4].value}
                inputChange={props.inputChange}
              />
            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default TemplateDisplay;
