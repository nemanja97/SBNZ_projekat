import React, { useState, useEffect } from "react";
import AdminNavbar from "../shared/AdminNavbar";
import { RulesService } from "../services/RulesService";
import RulesTable from "./rules/RulesTable";
import { useHistory } from "react-router-dom";

function RulesDashboard() {
  const [rules, setRules] = useState();
  const history = useHistory();

  useEffect(() => {
    async function fetchRules() {
      const response = await RulesService.getAll();
      console.log(response.data);
      setRules(response.data);
    }

    fetchRules();
  }, []);

  const handleDelete = async (path, name) => {
    await RulesService.remove(path).then(
      setRules(rules.filter((r) => r.path != name))
    );
  };

  return (
    <>
      <AdminNavbar />
      <div class="container">
        <RulesTable rules={rules} delete={handleDelete} />
        <button
          class="button is-fullwidth is-primary"
          style={{ marginBottom: 10 }}
          onClick={() => history.push(`/admin/rule`)}
        >
          Create new rule
        </button>
      </div>
    </>
  );
}

export default RulesDashboard;
