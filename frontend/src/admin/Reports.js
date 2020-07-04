import React, { useState } from "react";
import Moment from "moment";
import momentLocalizer from "react-widgets-moment";
import AdminNavbar from "../shared/AdminNavbar";
import { DateTimePicker } from "react-widgets";
import { ReportsService } from "../services/ReportsService";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Legend,
  Tooltip,
  Brush,
} from "recharts";

Moment.locale("en");
momentLocalizer();

function Reports() {
  const [query, setQuery] = useState({
    startDate: undefined,
    endDate: undefined,
  });
  const [report, setReport] = useState();

  const handleChange = (name) => (val) => {
    setQuery({ ...query, [name]: val });
  };

  const handleSubmit = async () => {
    const response = await ReportsService.getFinancialReports(query);
    console.log(response);
    setReport(response.data);
  };

  return (
    <>
      <AdminNavbar />
      <div class="container">
        <div
          className="columns is-centered"
          style={{ marginTop: "1%", marginLeft: "3%", marginBottom: "3%" }}
        >
          <div className="column is-narrow">
            <div className="control">
              <DateTimePicker
                placeholder="Start date"
                onChange={handleChange("startDate")}
              />
            </div>
          </div>
          <div className="column is-narrow">
            <div className="control">
              <DateTimePicker
                placeholder="End date"
                onChange={handleChange("endDate")}
              />
            </div>
          </div>
          <div className="column is-narrow">
            <div className="control">
              <button className="button is-primary" onClick={handleSubmit}>
                Submit
              </button>
            </div>
          </div>
        </div>
        <div className="columns is-centered">
          <div className="column is-narrow">
            {report && report.count > 0 && (
              <>
                <BarChart
                  width={1000}
                  height={600}
                  data={report.propertyList}
                  margin={{
                    top: 5,
                    right: 30,
                    left: 20,
                    bottom: 5,
                  }}
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="modifiedDate" />
                  <YAxis />
                  <Tooltip content={<CustomTooltip />} />
                  <Legend />
                  <Brush dataKey="modifiedDate" height={30} stroke="#00d1b2" />
                  <Bar dataKey="price" barSize={20} fill="#00d1b2" />
                </BarChart>
                <div class="box">
                  <article class="media">
                    <div class="media-content">
                      <div class="content">
                        <p>
                          <strong>Total price:</strong>{" "}
                          <small>{report.totalPrice}</small>
                          <br />
                          <strong>Minimum price:</strong>{" "}
                          <small>{report.minPrice}</small>
                          <br />
                          <strong>Average price:</strong>{" "}
                          <small>{report.avgPrice}</small>
                          <br />
                          <strong>Maximum price:</strong>{" "}
                          <small>{report.maxPrice}</small>
                          <br />
                          Report made concerning properties sold from {Moment(report.from).format("dddd, MMMM Do  YYYY")} up to {Moment(report.to).format("dddd, MMMM Do  YYYY")}
                        </p>
                      </div>
                    </div>
                  </article>
                </div>
              </>
            )}
            {report && report.count == 0 && <h1>No data found.</h1>}
          </div>
        </div>
      </div>
    </>
  );
}

export default Reports;

const CustomTooltip = (props) => {
  const { active, payload, label } = props;

  return (
    <>
      {active && (
        <div className="custom-tooltip">
          <p className="label">Property info</p>
          <div>
            <p className="desc">{`Price: ${payload[0].payload.price}`}</p>
            <p className="desc">{`Size: ${payload[0].payload.size}`}</p>
            <p className="desc">{`Number of beds: ${payload[0].payload.numberOfBeds}`}</p>
            <p className="desc">{`Number of bathrooms: ${payload[0].payload.numberOfBathrooms}`}</p>
          </div>
        </div>
      )}
    </>
  );
};
