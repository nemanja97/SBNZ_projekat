import React, { useState, useEffect } from "react";
import AdminNavbar from "../shared/AdminNavbar";
import { RulesService } from "../services/RulesService";
import RulesTable from "./rules/RulesTable";

function RulesDashboard() {
  const [rules, setRules] = useState();

  useEffect(() => {
    async function fetchRules() {
      const response = await RulesService.getAll();
      console.log(response.data);
      setRules(response.data);
    }

    fetchRules();
  }, []);

  return (
    <>
      <AdminNavbar />
      <RulesTable
        rules={rules}
      />
    </>
  );
}

export default RulesDashboard;
