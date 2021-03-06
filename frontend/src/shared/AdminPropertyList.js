import React from "react";
import AdminPropertyBox from "./AdminPropertyBox";

function AdminPropertyList(props) {
  return (
    <>
      {props.properties &&
        props.properties.map((property) => {
          return (
            <AdminPropertyBox property={property} edit={props.edit}/>
          );
        })}
    </>
  );
}

export default AdminPropertyList;
