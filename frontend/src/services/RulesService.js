import axios from "axios";

export const RulesService = {
  getAll,
  validateRule
};

async function getAll() {
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/rules`);
}

async function validateRule(rule) {
  return await axios.post(
    `${process.env.REACT_APP_API_URL}/v1/rules/validate`,
    rule
  );
}