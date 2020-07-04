import axios from "axios";

export const AnalyticsService = {
  moreInfoClick,
};

async function moreInfoClick(id) {
  return await axios.post(
    `${process.env.REACT_APP_API_URL}/v1/analytics/property/moreInfo/${id}`, {}
  );
}
