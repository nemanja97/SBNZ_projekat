import axios from "axios";

export const PropertyService = {
  getAll,
  get,
  addProperty,
  updateProperty,
  priceRecommendation,
  findOptimal,
};

async function getAll(status) {
  if (status)
    return await axios.get(`${process.env.REACT_APP_API_URL}/v1/properties?status=${status}`);
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/properties`);
}

async function get(id) {
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/properties/${id}`);
}

async function addProperty(property) {
  return await axios.post(`${process.env.REACT_APP_API_URL}/v1/properties`, property);
}

async function updateProperty(id, property) {
  return await axios.put(`${process.env.REACT_APP_API_URL}/v1/properties/${id}`, property);
}

async function priceRecommendation(property) {
  return await axios.post(`${process.env.REACT_APP_API_URL}/v1/properties/recommend`, property);
}

async function findOptimal(smartSearch) {
  return await axios.get(
    `${process.env.REACT_APP_API_URL}/v1/properties/optimal?=${smartSearch}`
  );
}
