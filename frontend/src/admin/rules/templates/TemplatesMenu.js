import React from "react";

const TemplatesMenu = (props) => {
  return (
    <>
      <aside className="menu" style={{ height: "100vh" }}>
        <p className="menu-label">Templates</p>
        <ul className="menu-list">
          <li>
            <a
              className={props.activeTemplate === 0 ? "is-active" : undefined}
              onClick={() => props.setActiveTemplate(0)}
            >
              Blank template
            </a>
          </li>
          <li>
            <a
              className={props.activeTemplate === 1 ? "is-active" : undefined}
              onClick={() => props.setActiveTemplate(1)}
            >
              Amenity score calculation template
            </a>
          </li>
          <li>
            <a
              className={props.activeTemplate === 2 ? "is-active" : undefined}
              onClick={() => props.setActiveTemplate(2)}
            >
              Distance score calculation template
            </a>
          </li>
          <li>
            <a
              className={props.activeTemplate === 3 ? "is-active" : undefined}
              onClick={() => props.setActiveTemplate(3)}
            >
              Heating score calculation template
            </a>
          </li>
          <li>
            <a
              className={props.activeTemplate === 4 ? "is-active" : undefined}
              onClick={() => props.setActiveTemplate(4)}
            >
              Pet score calculation template
            </a>
          </li>
        </ul>
      </aside>
    </>
  );
};

export default TemplatesMenu;
