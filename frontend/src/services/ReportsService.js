import axios from "axios";
import { QueryService } from "./QueryService";

export const ReportsService = {
  getFinancialReports,
};

async function getFinancialReports(query) {
  const url_query = QueryService.formQuery(query);
  return await axios.get(
    `${process.env.REACT_APP_API_URL}/v1/reports?${url_query}`
  );
}
