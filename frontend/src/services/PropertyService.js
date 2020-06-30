import axios from "axios";

export const PropertyService = {
  getAll,
  // get,
  // getAllMultimedia,
  // getMultimedia,
  // addProperty,
  // addPropertyMultimedia,
  // updateProperty,
  // deleteProperty,
  // deletePropertyMultimedia,
  findOptimal,
};

async function getAll() {
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/properties`);
}

async function findOptimal(smartSearch) {
  return await axios.get(
    `${process.env.REACT_APP_API_URL}/v1/properties/optimal?=${smartSearch}`
  );
}
