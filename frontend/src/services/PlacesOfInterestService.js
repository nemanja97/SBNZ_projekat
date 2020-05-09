import axios from "axios"

export const PlacesOfInterestService = {
    getAll,
    // get,
    // add,
    // update
    // delete,
}

async function getAll() {
    return await axios.get(`${process.env.REACT_APP_API_URL}/v1/placesOfInterest`);
}
